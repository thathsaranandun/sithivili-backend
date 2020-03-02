package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Admin;
import com.example.demo.model.Client;
import com.example.demo.model.User;
import com.example.demo.model.Volunteer;
import com.example.demo.repos.UserRepository;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.SignUpResponse;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository users;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.enabled}")
    private boolean isJwtEnabled;

    private final String USER_TYPE_CLIENT = "Client";
    private final String USER_TYPE_VOL = "Volunteer";
    private final String USER_TYPE_ADMIN = "Admin";
    private final String MALE_VOLUNTEER_IMG = "https://cdn3.iconfinder.com/data/icons/business-avatar-1/512/7_avatar-512.png";
    private final String FEMALE_VOLUNTEER_IMG = "https://cdn3.iconfinder.com/data/icons/business-avatar-1/512/4_avatar-512.png";


    public List<User> getAllUsers(){
        return users.findAll();
    }

    public SignUpResponse signUp(Client user){
        String msg;
        user.setUsertype(USER_TYPE_CLIENT);
        log.info("User details received: {} ", user.toString());
        if (user.getMobile().equals("") || user.getUsername().equals("") || user.getPassword().equals("")) {
            msg = "Sign Up Failed. Please enter all details.";
        } else {
            Client existingUser = (Client) users.findByUsernameAndUsertype(user.getUsername(), USER_TYPE_CLIENT);
            log.info("Username already exist? {}",existingUser != null);
            if (existingUser == null) { //Success instance
                log.info("Creating new user...");
                msg = "Registration successful!";
                users.save(user);
            } else {
                msg = "Username already in use. Please use a different username";
                log.info("Username already exists.");
            }

        }

        return new SignUpResponse(user, msg);
    }

    public User getUser(int userId){
        User user = users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        log.info("Requested user details: {}", user.toString());
        return user;
    }

    public User updateUser(int userId,Client userDetails){
        log.info("Received update request for user {}",userId);
        Client user = (Client) users.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if (userDetails.getUsername() != null) {
            log.info("Updated username");
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getPassword() != null) {
            log.info("Updated password");
            user.setPassword(userDetails.getPassword());
        }
        if (userDetails.getMobile() != null) {
            log.info("Updated mobile");
            user.setMobile(userDetails.getMobile());
        }

        User updatedUser = users.save(user);
        return updatedUser;
    }

    public ResponseEntity<?> removeUser(int userId){
        User user = users.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        users.delete(user);
        return ResponseEntity.ok().build();
    }

    public LoginResponse login(User user){
        log.info("Logging request received");
        LoginResponse response = new LoginResponse();

        User foundUser = users.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        if(null != foundUser){
            response.setUser(foundUser);
            response.getUser().setPassword(null);
            response.setDbdata(true);

            if(isJwtEnabled) {
                final String token = jwtTokenUtil.generateToken(user);
                response.setToken(token);
            }
            return response;
        }
        log.info("Invalid user details...");
        return response;
    }

    public List<Volunteer> getAllVolunteers(){
        return users.findAllByUsertype("Volunteer");
    }

    @Override
    public Volunteer getVolunteer(int userId) {
        Volunteer volunteer = (Volunteer) users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", userId));
        return volunteer;
    }

    @Override
    public User registerVolunteer(Volunteer user) {
        Volunteer existingVolunteer = (Volunteer) users.findByUsernameAndUsertype(user.getUsername(),USER_TYPE_VOL);
        System.out.println(existingVolunteer);
        if(existingVolunteer==null){ //Success instance
            System.out.println("Adding new volunteer...");
            user.setUsertype(USER_TYPE_VOL);
            if("male".equals(user.getGender())){
                user.setImage(MALE_VOLUNTEER_IMG);
            }else {
                user.setImage(FEMALE_VOLUNTEER_IMG);
            }
            users.save(user);
        }else {
            System.out.println("Username already exists.");
        }
        return user;
    }

    @Override
    public Admin registerAdmin(Admin user) {
        Admin existingUser = (Admin) users.findByUsernameAndUsertype(user.getUsername(),USER_TYPE_ADMIN);
        System.out.println(existingUser);
        if(existingUser==null){ //Success instance
            System.out.println("Creating new admin...");
            user.setUsertype(USER_TYPE_ADMIN);
            users.save(user);
        }else {
            System.out.println("Username already exists.");
        }
        return user;
    }


}

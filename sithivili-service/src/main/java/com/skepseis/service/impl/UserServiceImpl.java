package com.skepseis.service.impl;

import com.skepseis.model.*;
import com.skepseis.model.helper.AES;
import com.skepseis.model.request.AddVolunteerRequest;
import com.skepseis.model.request.PasswordResetRequest;
import com.skepseis.service.exception.ResourceNotFoundException;
import com.skepseis.service.repos.ClientRepository;
import com.skepseis.service.repos.UserRepository;
import com.skepseis.model.response.LoginResponse;
import com.skepseis.model.response.SignUpResponse;
import com.skepseis.service.repos.VolunteerDetailsRepository;
import com.skepseis.service.security.JwtTokenUtil;
import com.skepseis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository users;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    VolunteerDetailsRepository volunteerDetailsRepository;

    @Value("${jwt.enabled}")
    private boolean isJwtEnabled;

    @Value("${user.verify.link}")
    private String verifyLink;

    @Value("${user.pwd.reset.link}")
    private String resetPwdLink;

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
        if (user.getEmail().equals("") || user.getUsername().equals("") || user.getPassword().equals("")) {
            msg = "Sign Up Failed. Please enter all details.";
        } else {
            Client emailClient = clientRepository.findByEmail(user.getEmail());
            if(null != emailClient){
                msg = "Email already registered. Please sign up using a different email address ";
                log.info("Email already exists.");
            }else {
                Client existingUser = (Client) users.findByUsernameAndUsertype(user.getUsername(), USER_TYPE_CLIENT);
                log.info("Username already exist? {}", existingUser != null);
                if (existingUser == null) { //Success instance
                    log.info("Creating new user...");
                    msg = "Registration successful!";
                    user.setVerified(false);
                    users.save(user);
                    ModelMap map = new ModelMap();
                    map.addAttribute("username", user.getUsername());
                    String link = verifyLink + "?username=" + user.getUsername();
                    map.addAttribute("link", link);
                    emailService.sendEmail(user.getEmail(), map, "user-verify-template");
                } else {
                    msg = "Username already in use. Please use a different username";
                    log.info("Username already exists.");
                }
            }

        }

        return new SignUpResponse(user, msg);
    }

    public User getUser(int userId){
        User user = users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        log.info("Requested user details: {}", user.toString());
        return user;
    }

    public User updateUser(Client userDetails){
        log.info("Received update request for user {}",userDetails.getUserid());
        Client user = (Client) users.findById(userDetails.getUserid()).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", userDetails.getUserid()));

        if (userDetails.getUsername() != null) {
            log.info("Updated username");
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getPassword() != null) {
            log.info("Updated password");
            user.setPassword(userDetails.getPassword());
        }
        if (userDetails.getEmail() != null) {
            log.info("Updated email");
            user.setEmail(userDetails.getEmail());
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
            foundUser.setLoginFlag(true);
            users.save(foundUser);
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
    public boolean registerVolunteer(AddVolunteerRequest addVolunteerRequest) {
        try {
            Volunteer existingVolunteer = (Volunteer) users.findByUsernameAndUsertype(addVolunteerRequest.getUsername(),USER_TYPE_VOL);
            if(existingVolunteer==null){ //Success instance
                Volunteer user = new Volunteer();
                log.info("Adding new volunteer...");
                user.setUsername(addVolunteerRequest.getUsername());
                user.setLoginFlag(false);
                user.setPwdAlreadyEncrypted(true);
                user.setPassword(addVolunteerRequest.getPassword());
                user.setUsertype(USER_TYPE_VOL);
                user.setGender(addVolunteerRequest.getGender());
                user.setEmail(addVolunteerRequest.getEmail());
                user.setVerified(true);
                if("male".equals(addVolunteerRequest.getGender())){
                    user.setImage(MALE_VOLUNTEER_IMG);
                }else {
                    user.setImage(FEMALE_VOLUNTEER_IMG);
                }
                users.save(user);
                VolunteerDetails volunteerDetails = new VolunteerDetails();
                volunteerDetails.setNic(addVolunteerRequest.getNic());
                volunteerDetails.setDateOfBirth(addVolunteerRequest.getDateOfBirth());
                volunteerDetails.setMobile(addVolunteerRequest.getMobile());
                volunteerDetails.setName(addVolunteerRequest.getName());
                volunteerDetails.setVolunteer(user);
                volunteerDetailsRepository.save(volunteerDetails);
                log.info("Volunteer details recorded successfully");
            }else {
                log.error("Username already exists.");
                return false;
            }
            return true;
        }catch (Exception e){
            log.error("Exception occurred while adding volunteer - {}",e);
            return false;
        }

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

    @Override
    public boolean sendPasswordResetEmail(String email) {
        Client user = clientRepository.findByEmail(email);
        ModelMap map = new ModelMap();
        if(null == user){
            return false;
        }
        map.addAttribute("username",user.getUsername());
        String link = resetPwdLink+user.getUsername();
        map.addAttribute("link",link);
        try{
            emailService.sendEmail(user.getEmail(),map,"password-reset-template");
            return true;
        }catch (Exception e){
            log.error("Exception occurred", e);
            return false;
        }
    }

    @Override
    public boolean resetPassword(PasswordResetRequest passwordResetRequest) {
        log.info("Username - {}",passwordResetRequest.getUsername());
        log.info("Password - {}",passwordResetRequest.getPassword());
        try {
            User user = users.findByUsername(passwordResetRequest.getUsername());
            if(user!=null) {
                log.info("found user");
                user.setPwdAlreadyEncrypted(true);
                user.setPassword(passwordResetRequest.getPassword());
                users.save(user);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void verifyUser(String username) {
        Client client = (Client) users.findByUsernameAndUsertype(username, USER_TYPE_CLIENT);
        if(client != null){
            client.setVerified(true);
            users.save(client);
            log.info("Client {} has been verified.",username);
        }

    }

    @Override
    public boolean logout(Integer id) {
        try {
            User user = users.getOne(id);
            user.setLoginFlag(false);
            users.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}

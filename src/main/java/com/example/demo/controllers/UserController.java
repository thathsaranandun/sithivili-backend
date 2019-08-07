package com.example.demo.controllers;

import com.example.demo.LoginResponse;
import com.example.demo.SignUpResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.model.User;
import com.example.demo.model.Volunteer;
import com.example.demo.repos.UserRepository;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final String USER_TYPE = "Client";

    @Autowired
    UserRepository users;

    //Get all Users
    @GetMapping("/all")
    public List<User> getUsers()
    {
        return users.findAll();
    }

    // Create a New User
    @PostMapping("/user/new")
    public SignUpResponse createUser(@Valid @RequestBody Client user) {
        user.setUsertype(USER_TYPE);
        System.out.println("Creating new user...");
        users.save(user);
        return new SignUpResponse(user,"Registration successful!");
    }

    // Get a Single User
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") int userId) {
        User user = users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        System.out.println("Requested user details: "+user.toString());
        return user;

    }

    // Update User
    @PutMapping("/user/update/{id}")
    public User updateUser(@PathVariable(value = "id") int userId,
                           @Valid @RequestBody Client userDetails) {

        Client user = (Client) users.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if(userDetails.getUsername()!=null){
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getPassword()!=null){
            user.setPassword(userDetails.getPassword());
        }
        if (userDetails.getMobile()!=null){
            user.setMobile(userDetails.getMobile());
        }

        User updatedUser = users.save(user);
        return updatedUser;
    }

    // Delete a User
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") int userId) {
        User user = users.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        users.delete(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("user/login")
    public LoginResponse loginVerification(@Valid @RequestBody User user){
        System.out.println("Getting user list from db...");
        List<User> allUsers = getUsers();
        LoginResponse response = new LoginResponse();
        for(User selectedUser: allUsers){
            System.out.println("Checking users...");
            if(selectedUser.getUsername().equals(user.getUsername()) && selectedUser.getPassword().equals(user.getPassword())){
                System.out.println("Valid login. Sending data...");
                User dbuser = getUserById(selectedUser.getUserid());
                response.setUser(dbuser);
                response.getUser().setPassword(null);
                response.setDbdata(true);
                return response;

            }
        }
        System.out.println("Invalid user details...");
        return response;
    }

    //Get all Users
    @GetMapping("/volunteers/all")
    public List<Volunteer> getAllVolunteers()
    {
        System.out.println(users.findAllVolunteers());
        return users.findAllVolunteers();
    }



}

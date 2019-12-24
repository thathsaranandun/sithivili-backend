package com.example.demo.controllers;

import com.example.demo.LoginResponse;
import com.example.demo.SignUpResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.helper.AuthorizationHelper;
import com.example.demo.model.Client;
import com.example.demo.model.User;
import com.example.demo.model.Volunteer;
import com.example.demo.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final String USER_TYPE = "Client";

    @Autowired
    UserRepository users;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //Get all Users
    @GetMapping(Path.ALL_USERS)
    public List<User> getUsers(@RequestHeader Map<String, String> authorization) {
        AuthorizationHelper authorizationHelper = new AuthorizationHelper();
        authorizationHelper.authorizeHeader(authorization);
        return users.findAll();
    }

    // Create a New User
    @PostMapping(Path.NEW_USER)
    public SignUpResponse createUser(@Valid @RequestBody Client user,@RequestHeader Map<String, String> authorization) {
        AuthorizationHelper authorizationHelper = new AuthorizationHelper();
        authorizationHelper.authorizeHeader(authorization);
        String msg;
        user.setUsertype(USER_TYPE);
        logger.info("User details received: {} ", user.toString());
        if (user.getMobile().equals("") || user.getUsername().equals("") || user.getPassword().equals("")) {
            msg = "Sign Up Failed. Please enter all details.";
        } else {
            Client existingUser = (Client) users.findClientByName(user.getUsername());
            logger.info("Username already exist? {}",existingUser != null);
            if (existingUser == null) { //Success instance
                logger.info("Creating new user...");
                msg = "Registration successful!";
                users.save(user);
            } else {
                msg = "Username already in use. Please use a different username";
                logger.info("Username already exists.");
            }

        }

        return new SignUpResponse(user, msg);
    }

    // Get a Single User
    @GetMapping(Path.USER)
    public User getUserById(@PathVariable(value = "id") int userId,@RequestHeader Map<String, String> authorization) {
        AuthorizationHelper authorizationHelper = new AuthorizationHelper();
        authorizationHelper.authorizeHeader(authorization);
        User user = users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        logger.info("Requested user details: {}", user.toString());
        return user;

    }

    // Update User
    @PutMapping(path = Path.UPDATE_USER,consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public User updateUser(@PathVariable(value = "id") int userId,
                           @Valid @RequestBody Client userDetails,@RequestHeader Map<String, String> authorization) {
        AuthorizationHelper authorizationHelper = new AuthorizationHelper();
        authorizationHelper.authorizeHeader(authorization);

        logger.info("Received update request for user {}",userId);
        Client user = (Client) users.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if (userDetails.getUsername() != null) {
            logger.info("Updated username");
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getPassword() != null) {
            logger.info("Updated password");
            user.setPassword(userDetails.getPassword());
        }
        if (userDetails.getMobile() != null) {
            user.setMobile(userDetails.getMobile());
        }

        User updatedUser = users.save(user);
        return updatedUser;
    }

    // Delete a User
    @DeleteMapping(Path.DELETE_USER)
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") int userId,@RequestHeader Map<String, String> authorization) {
        AuthorizationHelper authorizationHelper = new AuthorizationHelper();
        authorizationHelper.authorizeHeader(authorization);

        User user = users.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        users.delete(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping(Path.USER_LOGIN)
    public LoginResponse loginVerification(@Valid @RequestBody User user,@RequestHeader Map<String, String> authorization) {
        AuthorizationHelper authorizationHelper = new AuthorizationHelper();
        authorizationHelper.authorizeHeader(authorization);
        logger.info("Logging request received");
        List<User> allUsers = users.findAll();
        LoginResponse response = new LoginResponse();
        for (User selectedUser : allUsers) {
            if (selectedUser.getUsername().equals(user.getUsername()) && selectedUser.getPassword().equals(user.getPassword())) {
                logger.info("Valid login. Sending data...");
                User dbuser = users.findById(selectedUser.getUserid()).orElseThrow(() -> new ResourceNotFoundException("User", "id", selectedUser.getUserid()));
                response.setUser(dbuser);
                response.getUser().setPassword(null);
                response.setDbdata(true);
                return response;

            }
        }
        logger.info("Invalid user details...");
        return response;
    }

    //Get all Users
    @GetMapping(Path.ALL_VOLUNTEERS)
    public List<Volunteer> getAllVolunteers(@RequestHeader Map<String, String> authorization) {
        AuthorizationHelper authorizationHelper = new AuthorizationHelper();
        authorizationHelper.authorizeHeader(authorization);
        return users.findAllVolunteers();
    }

    // Get a Single User
    @GetMapping(Path.VOLUNTEER)
    public Volunteer getVolunteerById(@PathVariable(value = "id") int userId,@RequestHeader Map<String, String> authorization) {
        AuthorizationHelper authorizationHelper = new AuthorizationHelper();
        authorizationHelper.authorizeHeader(authorization);
        Volunteer volunteer = (Volunteer) users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Volunteer", "id", userId));
        return volunteer;

    }


}

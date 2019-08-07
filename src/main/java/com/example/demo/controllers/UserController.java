package com.example.demo.controllers;

import com.example.demo.LoginResponse;
import com.example.demo.SignUpResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.model.User;
import com.example.demo.model.Volunteer;
import com.example.demo.repos.UserRepository;
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
    @GetMapping(Path.ALL_USERS)
    public List<User> getUsers() {
        return users.findAll();
    }

    // Create a New User
    @PostMapping(Path.NEW_USER)
    public SignUpResponse createUser(@Valid @RequestBody Client user) {
        String msg;
        user.setUsertype(USER_TYPE);
        if (user.getMobile() == null || user.getUsername() == null || user.getPassword() == null) {
            msg = "Sign Up Failed.Please enter all details.";
        } else {
            System.out.println("Creating new user...");
            msg = "Registration successful!";
            users.save(user);
        }

        return new SignUpResponse(user, msg);
    }

    // Get a Single User
    @GetMapping(Path.USER)
    public User getUserById(@PathVariable(value = "id") int userId) {
        User user = users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        System.out.println("Requested user details: " + user.toString());
        return user;

    }

    // Update User
    @PutMapping(Path.UPDATE_USER)
    public User updateUser(@PathVariable(value = "id") int userId,
                           @Valid @RequestBody Client userDetails) {

        Client user = (Client) users.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if (userDetails.getUsername() != null) {
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getPassword() != null) {
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
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") int userId) {
        User user = users.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        users.delete(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping(Path.USER_LOGIN)
    public LoginResponse loginVerification(@Valid @RequestBody User user) {
        System.out.println("Getting user list from db...");
        List<User> allUsers = getUsers();
        LoginResponse response = new LoginResponse();
        for (User selectedUser : allUsers) {
            System.out.println("Checking users...");
            if (selectedUser.getUsername().equals(user.getUsername()) && selectedUser.getPassword().equals(user.getPassword())) {
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
    @GetMapping(Path.ALL_VOLUNTEERS)
    public List<Volunteer> getAllVolunteers() {
        System.out.println(users.findAllVolunteers());
        return users.findAllVolunteers();
    }


}

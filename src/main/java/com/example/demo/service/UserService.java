package com.example.demo.service;


import com.example.demo.model.Admin;
import com.example.demo.model.Client;
import com.example.demo.model.User;
import com.example.demo.model.Volunteer;
import com.example.demo.response.LoginResponse;
import com.example.demo.response.SignUpResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    /* Get all users*/
    List<User> getAllUsers();

    /* Client registration */
    SignUpResponse signUp(Client user);

    /* Get user by id */
    User getUser(int userId);

    /* Update user details */
    User updateUser(int userId,Client userDetails);

    /* Delete user */
    ResponseEntity<?> removeUser(int userId);

    /* User authentication */
    LoginResponse login(User user);

    /* Get volunteer list */
    List<Volunteer> getAllVolunteers();

    /* Get Volunteer by id */
    Volunteer getVolunteer(int userId);

    /* Register new volunteer (Admin function)*/
    User registerVolunteer(Volunteer user);

    /* Register new admin (Admin function)*/
    Admin registerAdmin(Admin user);
}

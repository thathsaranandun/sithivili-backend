package com.example.demo.controllers;

import com.example.demo.model.Admin;
import com.example.demo.model.User;
import com.example.demo.model.Volunteer;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final String USER_TYPE = "Volunteer";

    @Autowired
    UserRepository users;

    //Create new volunteer
    @PostMapping(value = "/new/volunteer")
    public User addVolunteer(@Valid @RequestBody Volunteer user){
        user.setUsertype(USER_TYPE);
        return users.save(user);
    }

    @PostMapping(value="/new/admin")
    public Admin addAdmin(@Valid @RequestBody Admin user){
        user.setUsertype("Admin");
        return users.save(user);
    }
}

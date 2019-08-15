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

    private final String USER_TYPE_VOL = "Volunteer";
    private final String USER_TYPE_ADMIN = "Admin";

    @Autowired
    UserRepository users;

    //Create new volunteer
    @PostMapping(value = Path.NEW_VOLUNTEER)
    public User addVolunteer(@Valid @RequestBody Volunteer user){
        Volunteer existingVolunteer = (Volunteer) users.findVolunteerByName(user.getUsername());
        System.out.println(existingVolunteer);
        if(existingVolunteer==null){
            System.out.println("Adding new volunteer...");
            user.setUsertype(USER_TYPE_VOL);
            users.save(user);
        }else {
            System.out.println("Username already exists.");
        }
        return user;
    }

    //Create new admin user
    @PostMapping(value=Path.NEW_ADMIN)
    public Admin addAdmin(@Valid @RequestBody Admin user){
        Admin existingUser = (Admin) users.findAdminByName(user.getUsername());
        System.out.println(existingUser);
        if(existingUser==null){
            System.out.println("Creating new admin...");
            user.setUsertype(USER_TYPE_ADMIN);
            users.save(user);
        }else {
            System.out.println("Username already exists.");
        }
        return user;
    }
}

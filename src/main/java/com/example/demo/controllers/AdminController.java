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
    private final String MALE_VOLUNTEER_IMG = "https://cdn3.iconfinder.com/data/icons/business-avatar-1/512/7_avatar-512.png";
    private final String FEMALE_VOLUNTEER_IMG = "https://cdn3.iconfinder.com/data/icons/business-avatar-1/512/4_avatar-512.png";
    @Autowired
    UserRepository users;

    //Create new volunteer
    @PostMapping(value = Path.NEW_VOLUNTEER)
    public User addVolunteer(@Valid @RequestBody Volunteer user){
        Volunteer existingVolunteer = (Volunteer) users.findVolunteerByName(user.getUsername());
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

    //Create new admin user
    @PostMapping(value=Path.NEW_ADMIN)
    public Admin addAdmin(@Valid @RequestBody Admin user){
        Admin existingUser = (Admin) users.findAdminByName(user.getUsername());
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

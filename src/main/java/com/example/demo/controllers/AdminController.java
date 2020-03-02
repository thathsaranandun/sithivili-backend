package com.example.demo.controllers;

import com.example.demo.model.Admin;
import com.example.demo.model.User;
import com.example.demo.model.Volunteer;
import com.example.demo.repos.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {


    @Autowired
    UserServiceImpl userService;

    /**
     * Create new volunteer
     * @param user
     * @return User
     */
    @PostMapping(value = Path.NEW_VOLUNTEER)
    public User addVolunteer(@Valid @RequestBody Volunteer user){
        return userService.registerVolunteer(user);
    }

    /**
     * Create new admin user
     * @param user
     * @return Admin
     */
    @PostMapping(value=Path.NEW_ADMIN)
    public Admin addAdmin(@Valid @RequestBody Admin user){
        return userService.registerAdmin(user);
    }
}

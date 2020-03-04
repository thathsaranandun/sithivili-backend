package com.skepseis.demo.controllers;

import com.skepseis.model.Admin;
import com.skepseis.model.User;
import com.skepseis.model.Volunteer;
import com.skepseis.service.impl.UserServiceImpl;
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

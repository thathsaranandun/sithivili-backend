package com.skepseis.demo.controllers;

public interface Path {
    //Get all users
    String ALL_USERS = "/all";

    //Create a new user
    String NEW_USER = "/user/new";

    //Get user by id
    String USER = "/user/{id}";

    //Update user
    String UPDATE_USER = "/user/update/{id}";

    //Delete user
    String DELETE_USER = "/user/delete/{id}";

    //User login
    String USER_LOGIN = "user/login";

    //Get all volunteers
    String ALL_VOLUNTEERS = "/volunteers/all";

    //Create a new volunteer
    String NEW_VOLUNTEER = "/new/volunteer";

    //Create a new admin user
    String NEW_ADMIN = "/new/admin";

    //Get user by id
    String VOLUNTEER = "/volunteer/{id}";

    //Get all locations
    String LOCATIONS = "/location/all";

    //New location
    String NEW_LOCATION = "/location/new";

    //verify user
    String USER_VERIFY = "/user/verify";

    String LOG_OUT = "/user/logout/{id}";

    String PASSWORD_RESET_EMAIL = "/user/email/reset";

    String PASSWORD_RESET = "reset/password";
}

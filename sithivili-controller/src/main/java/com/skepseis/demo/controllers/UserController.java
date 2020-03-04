package com.skepseis.demo.controllers;

import com.skepseis.model.response.LoginResponse;
import com.skepseis.model.response.SignUpResponse;
import com.skepseis.demo.helper.AuthorizationHelper;
import com.skepseis.model.Client;
import com.skepseis.model.User;
import com.skepseis.model.Volunteer;
import com.skepseis.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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




    @Autowired
    AuthorizationHelper authorizationHelper;

    @Autowired
    UserServiceImpl userService;


    /**
     * Get all Users
     * @param bearer
     * @return List of users
     */
    @GetMapping(Path.ALL_USERS)
    @ApiOperation(value = "Get all users", authorizations = { @Authorization(value="apiKey") })
    public List<User> getUsers(@RequestHeader Map<String, String> bearer) {
        authorizationHelper.authorizeHeader(bearer);
        return userService.getAllUsers();
    }



    /**
     * Create a New User
     * @param user
     * @param bearer
     * @return SignUpResponse
     */
    @PostMapping(Path.NEW_USER)
    public SignUpResponse createUser(@Valid @RequestBody Client user,@RequestHeader Map<String, String> bearer) {
        authorizationHelper.authorizeHeader(bearer);
        return userService.signUp(user);
    }

    /**
     * Get a Single User
     * @param userId
     * @param bearer
     * @return User
     */
    @GetMapping(Path.USER)
    public User getUserById(@PathVariable(value = "id") int userId,@RequestHeader Map<String, String> bearer) {
        authorizationHelper.authorizeHeader(bearer);
        return userService.getUser(userId);

    }

    /**
     * Update User
     * @param userId
     * @param userDetails
     * @param bearer
     * @return User
     */
    @PutMapping(path = Path.UPDATE_USER,consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public User updateUser(@PathVariable(value = "id") int userId,
                           @Valid @RequestBody Client userDetails,@RequestHeader Map<String, String> bearer) {
        authorizationHelper.authorizeHeader(bearer);
        return userService.updateUser(userId,userDetails);
    }


    /**
     * Delete a User
     * @param userId
     * @param bearer
     * @return ResponseEntity
     */
    @DeleteMapping(Path.DELETE_USER)
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") int userId,@RequestHeader Map<String, String> bearer) {
        authorizationHelper.authorizeHeader(bearer);
        return userService.removeUser(userId);
    }

    /**
     * User Login
     * @param user
     * @param bearer
     * @return LoginResponse
     */
    @PostMapping(Path.USER_LOGIN)
    public LoginResponse loginVerification(@Valid @RequestBody User user,@RequestHeader Map<String, String> bearer) {
        authorizationHelper.authorizeHeader(bearer);
        return userService.login(user);
    }

    /**
     * Get all Users
     * @param bearer
     * @return
     */
    @GetMapping(Path.ALL_VOLUNTEERS)
    public List<Volunteer> getAllVolunteers(@RequestHeader Map<String, String> bearer) {
        authorizationHelper.authorizeHeader(bearer);
        return userService.getAllVolunteers();
    }

    /**
     * Get a Single User
     * @param userId
     * @param bearer
     * @return
     */
    @GetMapping(Path.VOLUNTEER)
    public Volunteer getVolunteerById(@PathVariable(value = "id") int userId,@RequestHeader Map<String, String> bearer) {
        authorizationHelper.authorizeHeader(bearer);
        return userService.getVolunteer(userId);
    }

    @GetMapping("test1")
    public boolean resetPassword(@PathVariable(value = "email") int email,@RequestHeader Map<String, String> bearer){
        authorizationHelper.authorizeHeader(bearer);
        return true;
    }

    @GetMapping("test2")
    public boolean resetEmailConfirm(@PathVariable(value = "username") int username){
        return true;
    }


}

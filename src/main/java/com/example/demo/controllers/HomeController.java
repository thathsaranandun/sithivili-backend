package com.example.demo.controllers;

import com.example.demo.helper.AuthorizationHelper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class HomeController {

    @Autowired
    AuthorizationHelper authorizationHelper;

    /**
     * Test server status
     * @return
     */
    @GetMapping("/healthCheck")
    public String healthCheck(@RequestHeader Map<String, String> bearer){
        authorizationHelper.authorizeHeader(bearer);
        return "Sithivili Server Up and Running";
    }



}

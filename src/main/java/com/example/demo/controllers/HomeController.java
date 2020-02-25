package com.example.demo.controllers;

import com.example.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


@RestController
public class HomeController {

    /**
     * Test server status
     * @return
     */
    @GetMapping("/")
    public String home(){

        return "Server Up and Running";
    }



}

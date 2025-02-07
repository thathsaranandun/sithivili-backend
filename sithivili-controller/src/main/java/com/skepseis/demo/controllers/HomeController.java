package com.skepseis.demo.controllers;

import com.skepseis.demo.helper.AuthorizationHelper;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "Server status: Running.";
    }



}

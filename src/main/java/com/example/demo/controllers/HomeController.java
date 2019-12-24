package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    /**
     * Test server status
     * @return
     */
    @RequestMapping("")
    public String home(){
        return "Sithivili Server status - running";
    }
}

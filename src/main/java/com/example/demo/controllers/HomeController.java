package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    /**
     * Test server status
     * @return
     */
    @RequestMapping("/")
    public ResponseEntity<?> home(){

        return ResponseEntity.ok().build();
    }

}

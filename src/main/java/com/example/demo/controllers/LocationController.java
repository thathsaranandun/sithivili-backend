package com.example.demo.controllers;

import com.example.demo.model.Location;
import com.example.demo.repos.LocationRepository;
import com.example.demo.service.impl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    LocationServiceImpl locationService;

    /**
     * Get all locations
     * @return
     */
    @GetMapping(Path.LOCATIONS)
    public List<Location> getLocations(){
        return locationService.getAllLocations();
    }

    /**
     * Add new location
     * @param location
     * @return
     */
    @PostMapping(Path.NEW_LOCATION)
    public ResponseEntity<?> addLocation(@Valid @RequestBody Location location){
        return locationService.addNewLocation(location);
    }
}

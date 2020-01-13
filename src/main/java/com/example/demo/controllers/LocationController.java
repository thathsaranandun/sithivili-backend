package com.example.demo.controllers;

import com.example.demo.model.Location;
import com.example.demo.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private LocationRepository locationRepository;

    /**
     * Get all locations
     * @return
     */
    @GetMapping(Path.LOCATIONS)
    public List<Location> getLocations(){
        return locationRepository.findAll();
    }

    /**
     * Add new location
     * @param location
     * @return
     */
    @PostMapping(Path.NEW_LOCATION)
    public ResponseEntity<?> addLocation(@Valid @RequestBody Location location){
        if(
                location.getName()!=null &&
                location.getAddress() != null &&
                location.getLatitude() != null &&
                location.getLongitude() != null
        ) {
            locationRepository.save(location);
            return ResponseEntity.ok("Successfully added new location");

        }
        return ResponseEntity.unprocessableEntity().build();
    }
}

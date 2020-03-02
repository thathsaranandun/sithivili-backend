package com.example.demo.service;

import com.example.demo.model.Location;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface LocationService {
    List<Location> getAllLocations();
    ResponseEntity<?> addNewLocation(Location location);
}

package com.skepseis.service.impl;

import com.skepseis.model.Location;
import com.skepseis.service.repos.LocationRepository;
import com.skepseis.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public ResponseEntity<?> addNewLocation(Location location) {
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

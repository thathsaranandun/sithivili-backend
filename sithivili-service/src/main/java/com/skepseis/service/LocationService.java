package com.skepseis.service;

import com.skepseis.model.Location;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface LocationService {
    List<Location> getAllLocations();
    ResponseEntity<?> addNewLocation(Location location);
}

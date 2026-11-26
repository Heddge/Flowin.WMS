package com.flowin.wms.location;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {
    private final LocationService service;

    public LocationController(LocationService ser)
    {
        service = ser;
    }

    @GetMapping("/api/locations")
    public ResponseEntity<List<LocationDto.LocationResponse>> getLocations()
    {
        return ResponseEntity.ok().body(service.getLocations());
    }

    @GetMapping("/api/locations/{id}")
    public ResponseEntity<LocationDto.LocationResponse> getLocationById(@Valid @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.getLocationById(id));
    }

    @PostMapping("/api/locations")
    public ResponseEntity<LocationDto.LocationResponse> postLocation(@Valid @RequestBody LocationDto.LocationRequest request)
    {
        return ResponseEntity.status(201).body(service.postLocation(request));
    }
}

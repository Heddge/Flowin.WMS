package com.flowin.wms.zone;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZoneController {
    private final ZoneService service;

    public ZoneController(ZoneService ser)
    {
        service = ser;
    }

    @GetMapping("/api/zones")
    public ResponseEntity<List<ZoneDto.ZoneResponse>> getZones()
    {
        return ResponseEntity.ok().body(service.getZones());
    }

    @GetMapping("/api/zones/{id}")
    public ResponseEntity<ZoneDto.ZoneResponse> getZoneById(@Valid @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.getZoneById(id));
    }

    @PostMapping("/api/zones")
    public ResponseEntity<ZoneDto.ZoneResponse> postZone(@Valid @RequestBody ZoneDto.ZoneRequest zoneRequest)
    {
        return ResponseEntity.status(201).body(service.postZone(zoneRequest));
    }
}

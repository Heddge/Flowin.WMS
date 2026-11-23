package com.flowin.wms.rack;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RackController {
    private final RackService service;

    public RackController(RackService ser)
    {
        service = ser;
    }

    @GetMapping("/api/racks")
    public ResponseEntity<List<RackDto.RackResponse>> getRacks()
    {
        return ResponseEntity.ok().body(service.getRacks());
    }

    @GetMapping("/api/racks/{id}")
    public ResponseEntity<RackDto.RackResponse> getRackById(@Valid @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.getRackById(id));
    }

    @PostMapping("/api/racks")
    public ResponseEntity<RackDto.RackResponse> postRack(@Valid @RequestBody RackDto.RackRequest request)
    {
        return ResponseEntity.status(201).body(service.postRack(request));
    }
}

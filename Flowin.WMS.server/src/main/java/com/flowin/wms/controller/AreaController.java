package com.flowin.wms.controller;

import com.flowin.wms.dto.AreaDto;
import com.flowin.wms.service.AreaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AreaController {
    private final AreaService service;

    public AreaController(AreaService as)
    {
        service = as;
    }

    @GetMapping("/api/areas")
    public ResponseEntity<List<AreaDto.AreaResponse>> getAreas()
    {
        return ResponseEntity.ok().body(service.getAreas());
    }

    @GetMapping("/api/areas/{id}")
    public ResponseEntity<AreaDto.AreaResponse> getAreaById(@Valid @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.getAreaById(id));
    }

    @PostMapping("/api/areas")
    public ResponseEntity<AreaDto.AreaResponse> postArea(@Valid @RequestBody AreaDto.AreaRequest request)
    {
        return ResponseEntity.status(201).body(service.postArea(request));
    }
}

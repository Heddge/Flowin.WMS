package com.flowin.wms.controller;

import com.flowin.wms.dto.WarehouseDto.*;
import com.flowin.wms.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WarehouseController {
    private final WarehouseService service;

    public WarehouseController(WarehouseService ws)
    {
        service= ws;
    }

    @GetMapping("/api/warehouses")
    public ResponseEntity<List<WarehouseResponse>> getWarehouses()
    {
        return ResponseEntity.ok().body(service.getWarehouses());
    }

    @GetMapping("/api/warehouses/{name}")
    public ResponseEntity<WarehouseResponse> getWarehouseByName(@Valid @PathVariable("name") String name)
    {
        return ResponseEntity.ok().body(service.getWarehouseByName(name));
    }
}

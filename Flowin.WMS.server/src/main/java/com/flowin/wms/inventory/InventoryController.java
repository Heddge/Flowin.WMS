package com.flowin.wms.inventory;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {
    private final InventoryService service;

    public InventoryController(InventoryService ser)
    {
        service = ser;
    }

    @GetMapping("/api/inventory")
    public ResponseEntity<List<InventoryDto.InventoryResponse>> getInventory()
    {
        return ResponseEntity.ok().body(service.getInventory());
    }

    @GetMapping("/api/inventory/{skuId}")
    public ResponseEntity<InventoryDto.InventoryResponse> getInventoryOfSkuById(@Valid @PathVariable("skuId") Integer skuId)
    {
        return ResponseEntity.ok().body(service.getInventoryOfSkuById(skuId));
    }

    @GetMapping("/api/bins/{id}/balance")
    public ResponseEntity<List<InventoryDto.LocationInventoryResponse>> getBinBalance(@Valid @PathVariable("id") Integer binId)
    {
        return ResponseEntity.ok().body(service.getBinBalance(binId));
    }

    @GetMapping("/api/areas/{id}/balance")
    public ResponseEntity<List<InventoryDto.LocationInventoryResponse>> getAreaBalance(@Valid @PathVariable("id") Integer binId)
    {
        return ResponseEntity.ok().body(service.getAreaBalance(binId));
    }

    @PostMapping("/api/bins/{id}/add-sku")
    public ResponseEntity<InventoryDto.LocationInventoryResponse> addSkuToBin(@Valid @PathVariable("id") Integer id, @Valid @RequestBody InventoryDto.InventoryRequest request)
    {
        return ResponseEntity.status(201).body(service.addSkuToBin(id, request));
    }
}

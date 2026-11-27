package com.flowin.wms.inventory;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.flowin.wms.bin;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BinController {
    private final BinService service;

    public BinController(BinService ser)
    {
        service = ser;
    }

    @GetMapping("/api/bins")
    public ResponseEntity<List<BinDto.BinResponse>> getBins()
    {
        return ResponseEntity.ok().body(service.getBins());
    }

    @GetMapping("/api/bins/{id}")
    public ResponseEntity<BinDto.BinResponse> getBinById(@Valid @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.getBinById(id));
    }

    @PostMapping("/api/bins")
    public ResponseEntity<BinDto.BinResponse> postBin(@Valid @RequestBody BinDto.BinRequest request)
    {
        return ResponseEntity.status(201).body(service.postBin(request));
    }
}

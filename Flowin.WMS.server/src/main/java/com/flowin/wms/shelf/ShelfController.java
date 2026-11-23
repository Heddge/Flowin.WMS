package com.flowin.wms.shelf;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShelfController {
    private final ShelfService service;

    public ShelfController(ShelfService ser)
    {
        service = ser;
    }

    @GetMapping("/api/shelves")
    public ResponseEntity<List<ShelfDto.ShelfResponse>> getShelves()
    {
        return ResponseEntity.ok().body(service.getShelves());
    }

    @GetMapping("/api/shelves/{id}")
    public ResponseEntity<ShelfDto.ShelfResponse> getShelfById(@Valid @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.getShelfById(id));
    }

    @PostMapping("/api/shelves")
    public ResponseEntity<ShelfDto.ShelfResponse> postShelf(@Valid @RequestBody ShelfDto.ShelfRequest request)
    {
        return ResponseEntity.status(201).body(service.postShelf(request));
    }
}

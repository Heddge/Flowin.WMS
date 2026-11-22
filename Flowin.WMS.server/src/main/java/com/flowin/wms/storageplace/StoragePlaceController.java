package com.flowin.wms.storageplace;

import com.flowin.wms.storageplace.StoragePlaceDto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoragePlaceController {
    private final StoragePlaceService service;

    public StoragePlaceController(StoragePlaceService ser)
    {
        service = ser;
    }

    @GetMapping("/api/storageplaces")
    public ResponseEntity<List<StoragePlaceResponse>> getStoragePlaces()
    {
        return ResponseEntity.ok().body(service.getStoragePlaces());
    }


    @GetMapping("/api/storageplaces/{id}")
    public ResponseEntity<StoragePlaceResponse> getStoragePlaces(@Valid @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.getStoragePlaceById(id));
    }

    @PostMapping("/api/storageplaces")
    public ResponseEntity<StoragePlaceResponse> postStoragePlace(@Valid @RequestBody StoragePlaceRequest request)
    {
        return ResponseEntity.ok().body(service.postStoragePlace(request));
    }

    @DeleteMapping("/api/storageplaces/{id}")
    public ResponseEntity<StoragePlaceResponse> deactivateStoragePlace(@Valid @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.deactivateStoragePlace(id));
    }
}

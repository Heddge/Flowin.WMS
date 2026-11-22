package com.flowin.wms.areatype;

import com.flowin.wms.areatype.AreaTypeDto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AreaTypeController {
    private final AreaTypeService service;

    public AreaTypeController(AreaTypeService ats)
    {
        service = ats;
    }

    @GetMapping("/api/areatypes")
    public ResponseEntity<List<AreaTypeResponse>> getAreaTypes()
    {
        return ResponseEntity.ok(service.getAreaTypes());
    }

    @GetMapping("/api/areatypes/{name}")
    public ResponseEntity<AreaTypeResponse> getAreaTypeByName(@Valid @PathVariable("name") String name)
    {
        return ResponseEntity.ok(service.getAreaTypeByName(name));
    }

    @PostMapping("/api/areatypes")
    public ResponseEntity<AreaTypeResponse> postAreaType(@RequestBody AreaTypeRequest request)
    {
        return ResponseEntity.ok(service.postAreaType(request));
    }
}

package com.flowin.wms.workarea;

import com.flowin.wms.workarea.WorkAreaDto.*;
import com.flowin.wms.exception.WorkAreaNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkAreaController {
    private final WorkAreaService service;

    public WorkAreaController(WorkAreaService _was)
    {
        this.service = _was;
    }

    @GetMapping("/api/workareas")
    public ResponseEntity<List<WorkAreaResponse>> getWorkAreas()
    {
        var response = service.getWorkAreasDto();
        if (response.isEmpty())
            throw new WorkAreaNotFoundException("Work areas didnt found.",
                    "finder: all",
                    "WorkAreaController::getWorkAreas");

         return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/workareas/{id}")
    public ResponseEntity<WorkAreaResponse> getWorkAreaById(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.getWorkAreaById(id));
    }

    @PostMapping("/api/workareas")
    public ResponseEntity<WorkAreaResponse> postWorkArea(@Valid @RequestBody WorkAreaRequest request)
    {
        return ResponseEntity.ok().body(service.postWorkArea(request));
    }
}

package com.flowin.wms.controller;

import com.flowin.wms.dto.WorkAreaDto.*;
import com.flowin.wms.exception.WorkAreaNotFoundException;
import com.flowin.wms.service.WorkAreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}

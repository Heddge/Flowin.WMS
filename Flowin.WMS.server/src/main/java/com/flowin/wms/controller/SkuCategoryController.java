package com.flowin.wms.controller;

import com.flowin.wms.dto.SkuCategoryDto.*;
import com.flowin.wms.service.SkuCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SkuCategoryController {
    private final SkuCategoryService service;

    public SkuCategoryController(SkuCategoryService _scs)
    {
        service = _scs;
    }

    @GetMapping("/api/skucategories")
    public ResponseEntity<List<SkuCategoryResponse>> getSkuCategories()
    {
        return ResponseEntity.ok().body(service.getAllCategories());
    }

    @GetMapping("/api/skucategories/id/{id}")
    public ResponseEntity<SkuCategoryResponse> getSkuCategoryById(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.getCategoryById(id));
    }

    @GetMapping("/api/skucategories/name/{name}")
    public ResponseEntity<SkuCategoryResponse> getSkuCategoryByName(@PathVariable("name") String name)
    {
        return ResponseEntity.ok().body(service.getCategoryByName(name));
    }
}

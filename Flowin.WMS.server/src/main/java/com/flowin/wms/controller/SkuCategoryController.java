package com.flowin.wms.controller;

import com.flowin.wms.dto.SkuCategoryDto.*;
import com.flowin.wms.service.SkuCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @PostMapping("/api/skucategories")
    public ResponseEntity<SkuCategoryResponse> postSkuCategory(@Valid @RequestBody SkuCategoryRequest skuCategoryRequest)
    {
        // TODO ResponseEntity.created(URI);
//        return ResponseEntity.created(URI.create())
        return ResponseEntity.ofNullable(service.postSkuCategory(skuCategoryRequest));
    }

    @PutMapping("/api/skucategories/name/{oldName}")
    public ResponseEntity<SkuCategoryResponse> putSkuCategoryByName(@PathVariable("oldName") String oldName, @Valid @RequestBody SkuCategoryRequest skuCategoryRequest)
    {
        return ResponseEntity.ok().body(service.putSkuCategoryByName(oldName, skuCategoryRequest));
    }

    @PutMapping("/api/skucategories/id/{id}")
    public ResponseEntity<SkuCategoryResponse> putSkuCategoryById(@PathVariable("id") Integer id, @Valid @RequestBody SkuCategoryRequest skuCategoryRequest)
    {
        return ResponseEntity.ok().body(service.putSkuCategoryById(id, skuCategoryRequest));
    }

    @DeleteMapping("/api/skucategories/id/{id}")
    public ResponseEntity<SkuCategoryResponse> deleteSkuCategoryById(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok().body(service.deleteSkuCategoryById(id));
    }

    @DeleteMapping("/api/skucategories/name/{name}")
    public ResponseEntity<SkuCategoryResponse> deleteSkuCategoryByName(@PathVariable("name") String name)
    {
        return ResponseEntity.ok().body(service.deleteSkuCategoryByName(name));
    }
}

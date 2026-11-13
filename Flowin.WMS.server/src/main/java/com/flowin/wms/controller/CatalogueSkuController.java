package com.flowin.wms.controller;

import com.flowin.wms.dto.CatalogueSkuRequest;
import com.flowin.wms.dto.CatalogueSkuResponse;
import com.flowin.wms.service.CatalogueSkuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogueSkuController {
    private final CatalogueSkuService catalogueSkuService;

    public CatalogueSkuController(CatalogueSkuService _catalogueSkuService)
    {
        this.catalogueSkuService= _catalogueSkuService;
    }

    @GetMapping("/api/skus/{id}")
    public ResponseEntity<CatalogueSkuResponse> getSkuById(@PathVariable("id") Integer id)
    {
        return catalogueSkuService.getSkuByIdDto(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/skus")
    public List<CatalogueSkuResponse> getSkus()
    {
        return catalogueSkuService.getAllSkusDto();
    }

    @PostMapping("/api/skus")
    public ResponseEntity<CatalogueSkuResponse> postSkuToSkus(@RequestBody CatalogueSkuRequest catalogueSkuRequest)
    {
        return catalogueSkuService.postSkuToSkus(catalogueSkuRequest)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

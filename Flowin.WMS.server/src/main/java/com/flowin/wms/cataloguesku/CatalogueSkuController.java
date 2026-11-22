package com.flowin.wms.cataloguesku;

import com.flowin.wms.cataloguesku.CatalogueSkuDto.*;
import com.flowin.wms.exception.SkuNotFoundException;
import jakarta.validation.Valid;
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
        CatalogueSkuResponse result = catalogueSkuService.getSkuByIdDto(id);

        if (result == null)
            throw new SkuNotFoundException(
                    String.format("There isn`t sku with id %s", id),
                    id.toString(),
                    "CatalogueSkuService::getSkuById(catalogueId)"
            );

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/skus")
    public List<CatalogueSkuResponse> getSkus()
    {
        return catalogueSkuService.getAllSkusDto();
    }

    @PostMapping("/api/skus")
    public ResponseEntity<CatalogueSkuResponse> postSkuToSkus(@Valid @RequestBody CatalogueSkuRequest catalogueSkuRequest)
    {
        return ResponseEntity.status(200).body(catalogueSkuService.postSkuToSkus(catalogueSkuRequest));
//        return ResponseEntity.ofNullable(catalogueSkuService.postSkuToSkus(catalogueSkuRequest));
    }

    @PutMapping("/api/skus/{id}")
    public ResponseEntity<CatalogueSkuResponse> updateSku(@Valid @PathVariable("id") Integer id, @Valid @RequestBody CatalogueSkuRequest request)
    {
        return ResponseEntity.status(200).body(catalogueSkuService.updateSku(id, request));
    }

    @DeleteMapping("/api/skus/{id}")
    public ResponseEntity<CatalogueSkuResponse> deactivateSku(@Valid @PathVariable("id") Integer Id)
    {
        return ResponseEntity.ok(catalogueSkuService.deactivateSku(Id));
    }
}

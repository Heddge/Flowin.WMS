package com.flowin.wms.inventory;

import com.flowin.wms.cataloguesku.CatalogueSkuDto;
import com.flowin.wms.location.LocationDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class InventoryDto {
    public record LocationInventoryResponse(
            LocationDto.LocationResponse location,
            CatalogueSkuDto.CatalogueSkuResponse sku,
            Integer reservedCount,
            Integer count
    ){}
    public record InventoryResponse(
            CatalogueSkuDto.CatalogueSkuResponse sku,
            Integer reservedCount,
            Integer count
    ){}
    public record InventoryRequest(
            @NotNull
            @Min(value = 1)
            Integer locationId,
            @NotNull
            @Min(value = 1)
            Integer skuId,
            @NotNull
            @Min(value = 0)
            Integer reservedCount,
            @NotNull
            @Min(value = 0)
            Integer count
    ){}
}

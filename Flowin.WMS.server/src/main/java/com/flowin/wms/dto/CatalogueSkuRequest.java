package com.flowin.wms.dto;

public record CatalogueSkuRequest(
        int skuCode,
        String skuName,
        String skuCategoryName,
        int volume,
        int weight,
        int height,
        int width,
        int length
) {
}

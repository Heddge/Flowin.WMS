package com.flowin.wms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CatalogueSkuRequest(
        @NotNull(message = "VALIDATION ERROR. skuName value can`t be null.")
        String skuName,
        @NotNull(message = "VALIDATION ERROR. skuCategoryName value can`t be null.")
        String skuCategoryName,
        @Min(value = 0, message = "VALIDATION ERROR. volume value can`t be less than 0.")
        int volume,
        @Min(value = 0, message = "VALIDATION ERROR. weight value can`t be less than 0.")
        int weight,
        @Min(value = 0, message = "VALIDATION ERROR. height value can`t be less than 0.")
        int height,
        @Min(value = 0, message = "VALIDATION ERROR. width value can`t be less than 0.")
        int width,
        @Min(value = 0, message = "VALIDATION ERROR. length value can`t be less than 0.")
        int length
) {
}

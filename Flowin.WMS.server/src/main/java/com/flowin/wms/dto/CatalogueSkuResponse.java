package com.flowin.wms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CatalogueSkuResponse(
        @NotNull(message = "VALIDATION ERROR. skuCode value can`t be null.")
        @Min(value = 0, message = "VALIDATION ERROR. skuCode value can`t be less than 0.")
        int skuCode,
        @NotNull(message = "VALIDATION ERROR. skuName value can`t be null")
        @NotEmpty(message = "VALIDATION ERROR. skuName value can`t be empty")
        String skuName,
        @NotNull(message = "VALIDATION ERROR. skuCategoryName value can`t be null")
        @NotEmpty(message = "VALIDATION ERROR. skuCategoryName value can`t be empty")
        String skuCategoryName,
        @Min(value = 0, message = "VALIDATION ERROR. volume value can`t be less than 0.")
        int volume,
        //@Min(value = 0, message = "VALIDATION ERROR. weight value can not be less than 0.")
        //we do not need bean-annotations for basic types (int in java is not nullable)
        int weight,
        @Min(value = 0, message = "VALIDATION ERROR. height value can`t be less than 0.")
        int height,
        @Min(value = 0, message = "VALIDATION ERROR. width value can`t be less than 0.")
        int width,
        @Min(value = 0, message = "VALIDATION ERROR. length value can`t be less than 0.")
        int length
)
{ }

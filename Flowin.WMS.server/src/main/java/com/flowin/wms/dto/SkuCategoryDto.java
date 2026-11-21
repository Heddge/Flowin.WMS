package com.flowin.wms.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class SkuCategoryDto {
    public record SkuCategoryResponse(
            Integer skuCategoryId,
            String skuCategoryName
    )
    {}

    public record SkuCategoryRequest(
            @NotNull
            String skuCategoryName
    )
    {}
}

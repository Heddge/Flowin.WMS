package com.flowin.wms.skucategory;

import jakarta.validation.constraints.NotNull;

public class SkuCategoryDto {
    public record SkuCategoryResponse(
            Integer skuCategoryId,
            String skuCategoryName
    )
    {}

    public record SkuCategoryRequest(
            @NotNull(message = "VALIDATION ERROR. Value must be not null. SkuCategoryRequest::skuCategoryName")
            String skuCategoryName
    )
    {}
}

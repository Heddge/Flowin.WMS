package com.flowin.wms.skucategory;

import org.springframework.stereotype.Component;

@Component
public class SkuCategoryMapper {
    public SkuCategoryDto.SkuCategoryResponse toSkuCategoryResponse(SkuCategory skuCategory)
    {
        return new SkuCategoryDto.SkuCategoryResponse(
                skuCategory.getCategoryId(),
                skuCategory.getCategoryName()
        );
    }
}

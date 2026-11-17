package com.flowin.wms.mapper;

import com.flowin.wms.dto.SkuCategoryDto;
import com.flowin.wms.entity.SkuCategory;
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

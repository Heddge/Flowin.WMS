package com.flowin.wms.mapper;

import com.flowin.wms.dto.CatalogueSkuResponse;
import com.flowin.wms.entity.CatalogueSku;
import org.springframework.stereotype.Component;

@Component
public class CatalogueSkuMapper {
    public CatalogueSkuResponse toCatalogueSkuResponse(CatalogueSku catalogueSku)
    {
        return new CatalogueSkuResponse(
                catalogueSku.getSkuCode(),
                catalogueSku.getSkuName(),
                catalogueSku.getSkuCategory().getCategoryName(),
                catalogueSku.getVolume(),
                catalogueSku.getWeight(),
                catalogueSku.getHeight(),
                catalogueSku.getWidth(),
                catalogueSku.getLength()
        );
    }
}

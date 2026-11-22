package com.flowin.wms.cataloguesku;

import com.flowin.wms.cataloguesku.CatalogueSkuDto.*;
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
                catalogueSku.getLength(),
                catalogueSku.getIsActive()
        );
    }
}

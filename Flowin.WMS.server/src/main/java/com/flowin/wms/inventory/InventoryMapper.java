package com.flowin.wms.inventory;

import com.flowin.wms.cataloguesku.CatalogueSkuMapper;
import com.flowin.wms.location.LocationMapper;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {
    private final CatalogueSkuMapper catalogueSkuMapper;
    private final LocationMapper locationMapper;

    public InventoryMapper(CatalogueSkuMapper csMapper, LocationMapper lMap)
    {
        catalogueSkuMapper = csMapper;
        locationMapper = lMap;
    }


    public InventoryDto.InventoryResponse balanceToInventoryResponse(Balance balance)
    {
        return new InventoryDto.InventoryResponse(
                catalogueSkuMapper.toCatalogueSkuResponse(balance.getSku()), balance.getReservedCount(),
                balance.getCount()
        );
    }

    public InventoryDto.LocationInventoryResponse locationBalanceToInventoryResponse(LocationBalance balance)
    {
        return new InventoryDto.LocationInventoryResponse(locationMapper.toLocationResponse(balance.getLocation()),
                catalogueSkuMapper.toCatalogueSkuResponse(balance.getSku()), balance.getReservedCount(),
                balance.getCount());
    }
}

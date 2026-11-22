package com.flowin.wms.warehouse;

import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {
    public WarehouseDto.WarehouseResponse toWarehouseResponse(Warehouse wh)
    {
        return new WarehouseDto.WarehouseResponse(wh.getId(), wh.getName());
    }
}

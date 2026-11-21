package com.flowin.wms.mapper;

import com.flowin.wms.dto.WarehouseDto;
import com.flowin.wms.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {
    public WarehouseDto.WarehouseResponse toWarehouseResponse(Warehouse wh)
    {
        return new WarehouseDto.WarehouseResponse(wh.getId(), wh.getName());
    }
}

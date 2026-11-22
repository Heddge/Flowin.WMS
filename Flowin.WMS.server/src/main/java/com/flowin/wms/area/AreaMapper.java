package com.flowin.wms.area;

import com.flowin.wms.areatype.AreaTypeMapper;
import com.flowin.wms.warehouse.WarehouseMapper;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {
    private final WarehouseMapper warehouseMapper;
    private final AreaTypeMapper areaTypeMapper;

    public AreaMapper(WarehouseMapper whMap, AreaTypeMapper atMap)
    {
        this.warehouseMapper = whMap;
        this.areaTypeMapper = atMap;
    }

    public AreaDto.AreaResponse toAreaResponse(Area area)
    {
        return new AreaDto.AreaResponse(area.getAreaId(), warehouseMapper.toWarehouseResponse(area.getWarehouse()), areaTypeMapper.toAreaTypeResponse(area.getAreaType()));
    }
}

package com.flowin.wms.area;

import org.springframework.stereotype.Component;

@Component
public class AreaMapper {
    public AreaDto.AreaResponse toAreaResponse(Area area)
    {
        return new AreaDto.AreaResponse(area.getAreaId(), area.getWarehouse(), area.getAreaType());
    }
}

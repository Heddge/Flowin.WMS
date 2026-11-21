package com.flowin.wms.mapper;

import com.flowin.wms.dto.AreaDto;
import com.flowin.wms.entity.Area;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {
    public AreaDto.AreaResponse toAreaResponse(Area area)
    {
        return new AreaDto.AreaResponse(area.getAreaId(), area.getWarehouse(), area.getAreaType());
    }
}

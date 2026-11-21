package com.flowin.wms.mapper;

import com.flowin.wms.dto.AreaTypeDto;
import com.flowin.wms.entity.AreaType;

public class AreaTypeMapper {
    public AreaTypeDto.AreaTypeResponse toAreaTypeResponse(AreaType areaType)
    {
        return new AreaTypeDto.AreaTypeResponse(areaType.getId(), areaType.getTypeName());
    }
}

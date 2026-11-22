package com.flowin.wms.areatype;

import org.springframework.stereotype.Component;

@Component
public class AreaTypeMapper {
    public AreaTypeDto.AreaTypeResponse toAreaTypeResponse(AreaType areaType)
    {
        return new AreaTypeDto.AreaTypeResponse(areaType.getId(), areaType.getAreaTypeName());
    }
}

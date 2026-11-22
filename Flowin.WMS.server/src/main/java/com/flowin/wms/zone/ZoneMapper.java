package com.flowin.wms.zone;

import org.springframework.stereotype.Component;

@Component
public class ZoneMapper {
    public ZoneDto.ZoneResponse toZoneResponse(Zone response)
    {
        return new ZoneDto.ZoneResponse(response.getZoneId(), response.getZoneWorkArea(),
                response.getZoneCategory(), response.getZoneWarehouse());
    }
}

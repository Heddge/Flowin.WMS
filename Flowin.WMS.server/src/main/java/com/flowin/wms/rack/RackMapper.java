package com.flowin.wms.rack;

import com.flowin.wms.zone.ZoneMapper;
import org.springframework.stereotype.Component;

@Component
public class RackMapper {
    private final ZoneMapper zoneMapper;

    public RackMapper(ZoneMapper zoneMap)
    {
        zoneMapper = zoneMap;
    }

    public RackDto.RackResponse toRackResponse(Rack response)
    {
        return new RackDto.RackResponse(response.getRackId(),
                zoneMapper.toZoneResponse(response.getZone()), response.getActive());
    }
}

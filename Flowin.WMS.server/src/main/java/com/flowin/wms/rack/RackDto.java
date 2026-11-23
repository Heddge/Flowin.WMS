package com.flowin.wms.rack;

import com.flowin.wms.zone.ZoneDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RackDto {
    public record RackResponse(
            Integer rackId,
            ZoneDto.ZoneResponse zone,
            Boolean isActive
    ){}
    public record RackRequest(
            @NotNull
            @Min(value = 1)
            Integer zoneId
    ){}
}

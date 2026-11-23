package com.flowin.wms.shelf;

import com.flowin.wms.rack.RackDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ShelfDto {
    public record ShelfResponse(
            Integer shelfId,
            RackDto.RackResponse rack,
            Boolean isActive
    ){}
    public record ShelfRequest(
            @NotNull
            @Min(value = 1)
            Integer rackId
    ){}
}

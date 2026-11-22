package com.flowin.wms.workarea;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WorkAreaDto {
    public record WorkAreaResponse(
            @Min(value = 0)
            Integer id,
            @NotNull
            String name,
            @NotNull
            Boolean isActive
    )
    {}

    public record WorkAreaRequest(
            @NotNull
            String name
    )
    {}
}

package com.flowin.wms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WorkAreaDto {
    public record WorkAreaResponse(
            @Min(value = 0)
            Integer id,
            @NotNull
            String name
    )
    {}

    public record WorkAreaRequest(
            @NotNull
            String name
    )
    {}
}

package com.flowin.wms.workarea;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WorkAreaDto {
    public record WorkAreaResponse(
            @NotNull(message = "VALIDATION ERROR. Value must be not null. WorkAreaResponse::id")
            @Min(value = 0)
            Long id,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. WorkAreaResponse::name")
            String name,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. WorkAreaResponse::isActive")
            Boolean isActive
    )
    {}

    public record WorkAreaRequest(
            @NotNull(message = "VALIDATION ERROR. Value must be not null. WorkAreaRequest::name")
            String name
    )
    {}
}

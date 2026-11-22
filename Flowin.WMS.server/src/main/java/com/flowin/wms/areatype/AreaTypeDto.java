package com.flowin.wms.areatype;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AreaTypeDto {
    public record AreaTypeResponse(
            @NotNull
            @Min(value = 1)
            Integer areaTypeId,
            @NotNull
            @NotEmpty
            @NotBlank
            String areaTypeName
    ){}

    public record AreaTypeRequest(
            @NotNull
            @NotEmpty
            @NotBlank
            String areaTypeName
    ){}
}

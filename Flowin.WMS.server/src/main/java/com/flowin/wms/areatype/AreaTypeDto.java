package com.flowin.wms.areatype;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AreaTypeDto {
    public record AreaTypeResponse(
            @NotNull(message = "VALIDATION ERROR. Value must be not null. AreaTypeResponse::areaTypeId")
            @Min(value = 1)
            Integer areaTypeId,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. AreaTypeResponse::areaTypeName")
            @NotEmpty
            @NotBlank
            String areaTypeName
    ){}

    public record AreaTypeRequest(
            @NotNull(message = "VALIDATION ERROR. Value must be not null. AreaTypeRequest::areaTypeName")
            @NotEmpty
            @NotBlank
            String areaTypeName
    ){}
}

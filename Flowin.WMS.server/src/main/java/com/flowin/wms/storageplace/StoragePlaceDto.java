package com.flowin.wms.storageplace;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class StoragePlaceDto {
    public record StoragePlaceResponse(
            Long storagePlaceId,
            String storagePlaceInfo,
            Boolean isActive
    ){}

    public record StoragePlaceRequest(
            @NotNull(message = "VALIDATION ERROR. Value must be not null. StoragePlaceResponse::storagePlaceInfo")
            @NotBlank
            @NotEmpty
            String storagePlaceInfo,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. StoragePlaceRequest::needWorkArea")
            @DefaultValue(value = "true")
            Boolean needWorkArea
    ){}
}

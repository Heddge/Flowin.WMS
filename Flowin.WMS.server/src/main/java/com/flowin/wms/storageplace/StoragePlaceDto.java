package com.flowin.wms.storageplace;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class StoragePlaceDto {
    public record StoragePlaceResponse(
            Integer storagePlaceId,
            String storagePlaceInfo,
            Boolean isActive
    ){}

    public record StoragePlaceRequest(
            @NotNull(message = "VALIDATION ERROR. Value must be not null. WarehouseResponse::storagePlaceInfo")
            @NotBlank
            @NotEmpty
            String storagePlaceInfo
    ){}
}

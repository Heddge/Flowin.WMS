package com.flowin.wms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class StoragePlaceDto {
    public record StoragePlaceResponse(
            Integer storagePlaceId,
            String storagePlaceInfo
    ){}

    public record StoragePlaceRequest(
            @NotNull
            @NotBlank
            @NotEmpty
            String storagePlaceInfo
    ){}
}

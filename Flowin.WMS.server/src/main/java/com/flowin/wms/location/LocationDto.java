package com.flowin.wms.location;

import com.flowin.wms.storageplace.StoragePlace;
import com.flowin.wms.storageplace.StoragePlaceDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LocationDto {
    public record LocationResponse(
            Integer locationId,
            StoragePlaceDto.StoragePlaceResponse storagePlace,
            String address
    ){}
    public record LocationRequest(
            @NotNull
            @Min(value = 1)
            Integer storagePlaceId,
            @NotNull
            @NotEmpty
            @NotBlank
            String address
    ){}
}

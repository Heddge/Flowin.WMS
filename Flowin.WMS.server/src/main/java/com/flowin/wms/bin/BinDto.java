package com.flowin.wms.bin;

import com.flowin.wms.shelf.ShelfDto;
import com.flowin.wms.storageplace.StoragePlaceDto;

import java.time.Instant;

public class BinDto {
    public record BinResponse(
            Long binId,
            StoragePlaceDto.StoragePlaceResponse storagePlace,
            ShelfDto.ShelfResponse shelf,
            Instant lastUsedAt,
            Integer occupiedVolume,
            Integer maxVolume,
            Integer occupiedWeight,
            Integer maxWeight,
            Integer occupiedCount,
            Integer maxCount
    ){}

    public record BinRequest(
            Integer storagePlaceId,
            Integer shelfId,
            Integer maxVolume,
            Integer maxWeight,
            Integer maxCount
    ){}
}

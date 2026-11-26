package com.flowin.wms.location;

import com.flowin.wms.storageplace.StoragePlaceMapper;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    private final StoragePlaceMapper storagePlaceMapper;

    public LocationMapper(StoragePlaceMapper stMapper)
    {
        storagePlaceMapper = stMapper;
    }

    public LocationDto.LocationResponse toLocationResponse(Location location)
    {
        return new LocationDto.LocationResponse(location.getLocationId(), storagePlaceMapper.toStoragePlaceResponse(location.getStoragePlace()),
                location.getAddress());
    }
}

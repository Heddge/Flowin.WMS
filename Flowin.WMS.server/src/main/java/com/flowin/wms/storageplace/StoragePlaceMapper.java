package com.flowin.wms.storageplace;

import org.springframework.stereotype.Component;

@Component
public class StoragePlaceMapper {
    public StoragePlaceDto.StoragePlaceResponse toStoragePlaceResponse(StoragePlace response)
    {
        return new StoragePlaceDto.StoragePlaceResponse(response.getId(), response.getInfo(), response.getIsActive());
    }
}

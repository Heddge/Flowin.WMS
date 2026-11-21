package com.flowin.wms.mapper;

import com.flowin.wms.dto.StoragePlaceDto;
import com.flowin.wms.entity.StoragePlace;
import org.springframework.stereotype.Component;

@Component
public class StoragePlaceMapper {
    public StoragePlaceDto.StoragePlaceResponse toStoragePlaceResponse(StoragePlace response)
    {
        return new StoragePlaceDto.StoragePlaceResponse(response.getId(), response.getInfo());
    }
}

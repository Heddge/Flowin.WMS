package com.flowin.wms.bin;

import com.flowin.wms.shelf.ShelfMapper;
import com.flowin.wms.storageplace.StoragePlaceMapper;
import org.springframework.stereotype.Component;

@Component
public class BinMapper {
    private final StoragePlaceMapper storagePlaceMapper;
    private final ShelfMapper shelfMapper;

    public BinMapper(StoragePlaceMapper _storagePlaceMapper, ShelfMapper _shelfMapper)
    {
        storagePlaceMapper = _storagePlaceMapper;
        shelfMapper = _shelfMapper;
    }

    public BinDto.BinResponse toBinResponse(Bin response)
    {
        return new BinDto.BinResponse(response.getBinId(), storagePlaceMapper.toStoragePlaceResponse(response.getStoragePlace()),
                shelfMapper.toShelfResponse(response.getShelf()), response.getLastUsedAt(), response.getOccupiedVolume(),
                response.getMaxVolume(), response.getOccupiedWeight(), response.getMaxWeight(),
                response.getOccupiedCount(), response.getMaxCount());
    }
}

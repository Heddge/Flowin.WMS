package com.flowin.wms.shelf;

import com.flowin.wms.rack.RackMapper;
import org.springframework.stereotype.Component;

@Component
public class ShelfMapper {
    private final RackMapper rackMapper;

    public ShelfMapper(RackMapper rackMap)
    {
        rackMapper = rackMap;
    }

    public ShelfDto.ShelfResponse toShelfResponse(Shelf response)
    {
        return new ShelfDto.ShelfResponse(response.getShelfId(),
                rackMapper.toRackResponse(response.getRack()), response.getActive());
    }
}

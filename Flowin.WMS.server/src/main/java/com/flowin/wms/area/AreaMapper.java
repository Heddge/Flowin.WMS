package com.flowin.wms.area;

import com.flowin.wms.areatype.AreaTypeMapper;
import com.flowin.wms.storageplace.StoragePlaceMapper;
import com.flowin.wms.warehouse.WarehouseMapper;
import com.flowin.wms.workarea.WorkAreaMapper;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {
    private final WarehouseMapper warehouseMapper;
    private final AreaTypeMapper areaTypeMapper;
    private final WorkAreaMapper workAreaMapper;
    private final StoragePlaceMapper storagePlaceMapper;

    public AreaMapper(WarehouseMapper whMap, AreaTypeMapper atMap,
                      WorkAreaMapper waMap, StoragePlaceMapper spMap)
    {
        this.warehouseMapper = whMap;
        this.areaTypeMapper = atMap;
        this.workAreaMapper = waMap;
        this.storagePlaceMapper = spMap;
    }

    public AreaDto.AreaResponse toAreaResponse(Area area)
    {
        return new AreaDto.AreaResponse(area.getAreaId(),
                storagePlaceMapper.toStoragePlaceResponse(area.getStoragePlace()),
                workAreaMapper.toWorkAreaResponse(area.getWorkArea()),
                warehouseMapper.toWarehouseResponse(area.getWarehouse()),
                areaTypeMapper.toAreaTypeResponse(area.getAreaType()));
    }
}

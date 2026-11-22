package com.flowin.wms.zone;

import com.flowin.wms.skucategory.SkuCategoryMapper;
import com.flowin.wms.warehouse.WarehouseMapper;
import com.flowin.wms.workarea.WorkAreaMapper;
import org.springframework.stereotype.Component;

@Component
public class ZoneMapper {
    private final WorkAreaMapper workAreaMapper;
    private final SkuCategoryMapper skuCategoryMapper;
    private final WarehouseMapper warehouseMapper;

    public ZoneMapper(WorkAreaMapper wam, SkuCategoryMapper scMap, WarehouseMapper whMap)
    {
        workAreaMapper = wam;
        skuCategoryMapper = scMap;
        warehouseMapper = whMap;
    }

    public ZoneDto.ZoneResponse toZoneResponse(Zone response)
    {
        return new ZoneDto.ZoneResponse(response.getZoneId(), workAreaMapper.toWorkAreaResponse(response.getZoneWorkArea()),
                skuCategoryMapper.toSkuCategoryResponse(response.getZoneCategory()), warehouseMapper.toWarehouseResponse(response.getZoneWarehouse()));
    }
}

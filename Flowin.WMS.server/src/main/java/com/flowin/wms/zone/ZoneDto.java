package com.flowin.wms.zone;

import com.flowin.wms.skucategory.SkuCategoryDto;
import com.flowin.wms.warehouse.WarehouseDto;
import com.flowin.wms.workarea.WorkAreaDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ZoneDto {
    public record ZoneResponse(
            Integer zoneId,
            WorkAreaDto.WorkAreaResponse zoneWorkArea,
            SkuCategoryDto.SkuCategoryResponse zoneSkuCategory,
            WarehouseDto.WarehouseResponse zoneWh
    ){}

    public record ZoneRequest(
            @NotNull
            @Min(value = 1)
            Integer workAreaId,
            @NotNull
            @NotEmpty
            @NotBlank
            String skuCategoryName,
            @NotNull
            @Min(value = 1)
            Integer whId
    ){}
}

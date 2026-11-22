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
            @NotNull(message = "VALIDATION ERROR. Value must be not null. ZoneRequest::workAreaId")
            @Min(value = 1)
            Integer workAreaId,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. ZoneRequest::skuCategoryName")
            @NotEmpty
            @NotBlank
            String skuCategoryName,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. ZoneRequest::whId")
            @Min(value = 1)
            Integer whId
    ){}
}

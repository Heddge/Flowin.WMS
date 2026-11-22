package com.flowin.wms.zone;

import com.flowin.wms.skucategory.SkuCategory;
import com.flowin.wms.warehouse.Warehouse;
import com.flowin.wms.workarea.WorkArea;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ZoneDto {
    public record ZoneResponse(
            Integer zoneId,
            WorkArea zoneWorkArea,
            SkuCategory zoneSkuCategory,
            Warehouse zoneWh
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

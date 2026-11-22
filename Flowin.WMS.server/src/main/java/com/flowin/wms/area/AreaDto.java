package com.flowin.wms.area;

import com.flowin.wms.areatype.AreaType;
import com.flowin.wms.warehouse.Warehouse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AreaDto{
    public record AreaResponse(
            @NotNull
            @Min(value = 1)
            Integer areaId,
            @NotNull
            Warehouse warehouse,
            @NotNull
            AreaType areaType
    ){}

    public record AreaRequest(
            @NotNull
            @Min(value = 1)
            Integer areaId,
            @NotNull
            @Min(value = 1)
            Integer warehouseId,
            @NotNull
            @NotEmpty
            @NotBlank
            String areaType
    ){}
}

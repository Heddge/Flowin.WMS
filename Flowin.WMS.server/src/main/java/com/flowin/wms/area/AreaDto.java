package com.flowin.wms.area;

import com.flowin.wms.areatype.AreaType;
import com.flowin.wms.areatype.AreaTypeDto;
import com.flowin.wms.warehouse.Warehouse;
import com.flowin.wms.warehouse.WarehouseDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AreaDto{
    public record AreaResponse(
            @NotNull(message = "VALIDATION ERROR. Value must be not null. AreaResponse::areaId")
            @Min(value = 1)
            Integer areaId,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. AreaResponse::warehouse")
            WarehouseDto.WarehouseResponse warehouse,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. AreaResponse::areaType")
            AreaTypeDto.AreaTypeResponse areaType
    ){}

    public record AreaRequest(
            @NotNull(message = "VALIDATION ERROR. Value must be not null. AreaRequest::areaId")
            @Min(value = 1)
            Integer areaId,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. AreaRequest::warehouseId")
            @Min(value = 1)
            Integer warehouseId,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. AreaRequest::areaType")
            @NotEmpty
            @NotBlank
            String areaType
    ){}
}

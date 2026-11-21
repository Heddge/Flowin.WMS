package com.flowin.wms.dto;

public class AreaTypeDto {
    public record AreaTypeResponse(
            Integer areaTypeId,
            String areaTypeName
    ){}

    public record AreaTypeRequest(
            String areaTypeName
    ){}
}

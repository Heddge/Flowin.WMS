package com.flowin.wms.warehouse;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class WarehouseDto {
    public record WarehouseResponse(
            @Id
            @NotNull(message = "VALIDATION ERROR. Value must be not null. WarehouseResponse::id")
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Min(value = 1)
            Integer id,
            @NotNull(message = "VALIDATION ERROR. Value must be not null. WarehouseResponse::name")
            @NotBlank
            @NotEmpty
            String name
    ){}
    public record WarehouseRequest(
            @NotNull(message = "VALIDATION ERROR. Value must be not null. WarehouseResponse::name")
            @NotBlank
            @NotEmpty
            String name
    ){}
}

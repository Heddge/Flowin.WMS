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
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Min(value = 1)
            Integer id,
            @NotNull
            @NotBlank
            @NotEmpty
            String name
    ){}
    public record WarehouseRequest(
            @NotNull
            @NotBlank
            @NotEmpty
            String name
    ){}
}

package com.flowin.wms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "storage_places")
public class StoragePlace {
    @Id
    @NotNull
    @Min(value = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "storage_place_id")
    private Integer Id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "info")
    private String info;

    public StoragePlace() {}

    public StoragePlace(String _info)
    {
        info = _info;
    }

    public StoragePlace(Integer _id, String _info)
    {
        Id = _id;
        info = _info;
    }

    public Integer getId() {
        return Id;
    }

    public String getInfo() {
        return info;
    }
}

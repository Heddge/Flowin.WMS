package com.flowin.wms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @NotNull
    @Min(value = 1)
    @Column(name = "wh_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotNull
    @NotBlank
    @Column(name = "wh_name")
    private String whName;

    public Warehouse() {}

    public Warehouse(String _name)
    {
        whName = _name;
    }

    public Warehouse(Integer _id, String _name)
    {
        Id = _id;
        whName = _name;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return whName;
    }
}

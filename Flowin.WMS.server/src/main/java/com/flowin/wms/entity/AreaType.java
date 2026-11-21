package com.flowin.wms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "area_types")
public class AreaType {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_type_id")
    @Min(value = 1)
    private Integer Id;
    @NotNull
    @NotBlank
    @Column(name = "area_type_name")
    private String typeName;

    public AreaType() {}
    public AreaType(String _typeName)
    {
        typeName = _typeName;
    }

    public AreaType(Integer _id, String _typeName)
    {
        Id = _id;
        typeName= _typeName;
    }

    public Integer getId() {
        return Id;
    }

    public String getTypeName() {
        return typeName;
    }
}

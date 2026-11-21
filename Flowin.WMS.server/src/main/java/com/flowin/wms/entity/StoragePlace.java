package com.flowin.wms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Component;

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

    @NotNull
    @Column(name = "is_active")
    private Boolean isActive;

    public StoragePlace() {}

    public StoragePlace(String _info, Boolean _isActive)
    {
        info = _info;
        isActive = _isActive;
    }

    public StoragePlace(Integer _id, String _info,Boolean _isActive)
    {
        Id = _id;
        info = _info;
        isActive = _isActive;
    }

    public Integer getId() {
        return Id;
    }

    public String getInfo() {
        return info;
    }
    public Boolean getIsActive() {
        return isActive;
    }

    public void deactivate()
    {
        this.isActive = false;
    }
}

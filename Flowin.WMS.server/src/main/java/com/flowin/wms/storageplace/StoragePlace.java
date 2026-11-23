package com.flowin.wms.storageplace;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "storage_work_area_id_generator")
    @SequenceGenerator(name = "storage_work_area_id_generator",
    sequenceName = "storage_work_area_id_seq",
    allocationSize = 1)
    @Column(name = "storage_place_id")
    private Long Id;
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

    public Long getId() {
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

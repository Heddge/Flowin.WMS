package com.flowin.wms.area;

import com.flowin.wms.areatype.AreaType;
import com.flowin.wms.storageplace.StoragePlace;
import com.flowin.wms.workarea.WorkArea;
import com.flowin.wms.warehouse.Warehouse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "areas")
public class Area {
    @Id
    @NotNull
    @Min(value = 1)
    @Column(name = "area_id")
    private Integer areaId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    @NotNull
    private StoragePlace storagePlace;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    @NotNull
    private WorkArea workArea;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wh_id")
    private Warehouse warehouse;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_type_id")
    private AreaType areaType;

    public Area() {}

    public Area(StoragePlace storPlace, WorkArea workAr, Warehouse wh, AreaType type)
    {
        storagePlace= storPlace;
        workArea=workAr;
        warehouse = wh;
        areaType = type;
    }

    public Area(Integer id, StoragePlace storPlace, WorkArea workAr, Warehouse wh, AreaType type)
    {
        areaId = id;
        storagePlace= storPlace;
        workArea=workAr;
        warehouse = wh;
        areaType = type;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public AreaType getAreaType() {
        return areaType;
    }

    public StoragePlace getStoragePlace() {
        return storagePlace;
    }

    public WorkArea getWorkArea() {
        return workArea;
    }
}

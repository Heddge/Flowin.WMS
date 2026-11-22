package com.flowin.wms.zone;

import com.flowin.wms.skucategory.SkuCategory;
import com.flowin.wms.warehouse.Warehouse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="zones")
public class Zone {
    @Id
    @NotNull
    @Min(value = 1)
    @Column(name = "zone_id")
    private Integer zoneId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "zone_sku_category_id") -> needn't
    private SkuCategory zoneCategory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse zoneWarehouse;

    public Zone() {}

    public Zone(Integer _id, SkuCategory category, Warehouse warehouse)
    {
        zoneId = _id;
        zoneCategory = category;
        zoneWarehouse=warehouse;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public SkuCategory getZoneCategory() {
        return zoneCategory;
    }

    public Warehouse getZoneWarehouse() {
        return zoneWarehouse;
    }
}

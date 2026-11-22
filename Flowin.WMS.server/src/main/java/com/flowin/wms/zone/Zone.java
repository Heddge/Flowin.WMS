package com.flowin.wms.zone;

import com.flowin.wms.skucategory.SkuCategory;
import com.flowin.wms.warehouse.Warehouse;
import com.flowin.wms.workarea.WorkArea;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="zones")
public class Zone {
    @Id
    @NotNull(message = "VALIDATION ERROR. Value must be not null. Zone::zoneId")
    @Min(value = 1)
    @Column(name = "zone_id")
    private Integer zoneId;

    @NotNull(message = "VALIDATION ERROR. Value must be not null. Zone::zoneWorkArea")
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "zone_id")
    private WorkArea zoneWorkArea;

    @NotNull(message = "VALIDATION ERROR. Value must be not null. Zone::zoneCategory")
    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "zone_sku_category_id") -> need JoinColumn
    @JoinColumn(name = "zone_sku_category_id")
    private SkuCategory zoneCategory;

    @NotNull(message = "VALIDATION ERROR. Value must be not null. Zone::zoneWarehouse")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wh_id")
    private Warehouse zoneWarehouse;

    public Zone() {}

    public Zone(WorkArea workArea, SkuCategory category, Warehouse warehouse)
    {
        zoneWorkArea = workArea;
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
    public WorkArea getZoneWorkArea() {
        return zoneWorkArea;
    }
}

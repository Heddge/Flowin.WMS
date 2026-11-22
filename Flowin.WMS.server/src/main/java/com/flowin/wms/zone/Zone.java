package com.flowin.wms.zone;

import com.flowin.wms.skucategory.SkuCategory;
import com.flowin.wms.warehouse.Warehouse;
import com.flowin.wms.workarea.WorkArea;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.jdbc.Work;

@Entity
@Table(name="zones")
public class Zone {
    @Id
    @NotNull
    @Min(value = 1)
    @Column(name = "zone_id")
    private Integer zoneId;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private WorkArea zoneWorkArea;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "zone_sku_category_id") -> need JoinColumn
    @JoinColumn(name = "zone_sku_category_id")
    private SkuCategory zoneCategory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wh_id")
    private Warehouse zoneWarehouse;

    public Zone() {}

    public Zone(Integer _id, WorkArea workArea, SkuCategory category, Warehouse warehouse)
    {
        zoneId = _id;
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

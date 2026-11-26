package com.flowin.wms.inventory;

import com.flowin.wms.cataloguesku.CatalogueSku;
import com.flowin.wms.location.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "location_item_balances")
public class LocationBalance {
    @EmbeddedId
    private LocationBalanceId locationBalanceId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("locationId")
    @JoinColumn(name = "location_id")
    private Location location;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skuId")
    @JoinColumn(name = "sku_id")
    private CatalogueSku sku;

    @NotNull
    @Min(value = 0)
    private Integer reservedCount;
    @NotNull
    @Min(value = 0)
    private Integer count;

    public LocationBalance() {}

    public LocationBalance(LocationBalanceId id, Location _location, CatalogueSku _sku,
                           Integer _reservedCount, Integer _count)
    {
        locationBalanceId = id;
        location = _location;
        sku = _sku;
        reservedCount = _reservedCount;
        count = _count;
    }

    public LocationBalanceId getLocationBalanceId() {
        return locationBalanceId;
    }

    public Location getLocation() {
        return location;
    }

    public CatalogueSku getSku() {
        return sku;
    }

    public Integer getReservedCount() {
        return reservedCount;
    }

    public Integer getCount() {
        return count;
    }
}

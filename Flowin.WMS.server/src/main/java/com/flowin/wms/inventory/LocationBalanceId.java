package com.flowin.wms.inventory;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LocationBalanceId implements Serializable {
    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "sku_id")
    private Long skuId;

    public LocationBalanceId() {}

    public LocationBalanceId(Integer _locationId, Integer _skuId)
    {
        locationId = _locationId.longValue();
        skuId = _skuId.longValue();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (!(obj instanceof LocationBalanceId other))
            return false;

        return Objects.equals(locationId, other.locationId) && Objects.equals(skuId, other.skuId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(locationId, skuId);
    }
}

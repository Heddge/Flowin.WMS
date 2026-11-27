package com.flowin.wms.inventory;

import com.flowin.wms.cataloguesku.CatalogueSku;
import com.flowin.wms.exception.InvalidArgumentException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "item_balances")
public class Balance {
    @Id
    @Column(name = "sku_id")
    private Integer skuId;

    @OneToOne
    @JoinColumn(name = "sku_id")
    private CatalogueSku sku;

    @NotNull
    @Min(value = 0)
    private Integer reservedCount;

    @NotNull
    @Min(value = 0)
    private Integer count;

    public Balance(){}

    public Balance(CatalogueSku _sku, Integer _reservedCount, Integer _count)
    {
        sku = _sku;
        reservedCount = _reservedCount;
        count = _count;
    }

    public Integer getSkuId() {
        return skuId;
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

    public void increaseCount(Integer value)
    {
        if (value == null || value < 0)
            throw new InvalidArgumentException(
                    "ERROR: Invalid value for increasing count (was null or less than 0).",
                    "ARGUMENT: Requested to increase count of skus",
                    "PLACED: Entity-method."
            );

        this.count += value;
    }

    public void increaseReservedCount(Integer value)
    {
        if (value == null || value < 0)
            throw new InvalidArgumentException(
                    "ERROR: Invalid value for increasing count (was null or less than 0).",
                    "ARGUMENT: Requested to increase count of skus",
                    "PLACED: Entity-method."
            );

        if (this.reservedCount + value > this.count)
            throw new InvalidArgumentException(
                    "ERROR: Invalid value for increasing reservedCount (reservedCount + value > count).",
                    "ARGUMENT: Requested to increase reservedCount of skus",
                    "PLACED: Entity-method."
            );

        this.reservedCount += value;
    }
}

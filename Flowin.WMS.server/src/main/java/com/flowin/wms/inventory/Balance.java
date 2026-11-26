package com.flowin.wms.inventory;

import com.flowin.wms.cataloguesku.CatalogueSku;
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
}

package com.flowin.wms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "sku_categories")
public class SkuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skuCategoryId;
    @Min(value = 0)
    @Column(nullable = false, length = 255)
    private String skuCategoryName;

    public SkuCategory() {}

    public SkuCategory(String sku_category_name)
    {
        skuCategoryName = sku_category_name;
    }

    // getters
    public Integer getCategoryId() { return skuCategoryId; }
    public String getCategoryName() { return skuCategoryName; }
}

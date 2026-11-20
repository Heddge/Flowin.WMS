package com.flowin.wms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "catalogue_skus")
public class CatalogueSku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skuCode;
    @Column(nullable = false, length = 100)
    private String skuName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_category_id", nullable = false)
    private SkuCategory skuCategory;
    @Min(value = 0)
    @Column(nullable = false)
    private int volume;
    @Min(value = 0)
    @Column(nullable = false)
    private int weight;
    @Min(value = 0)
    @Column(nullable = false)
    private int height;
    @Min(value = 0)
    @Column(nullable = false)
    private int width;
    @Min(value = 0)
    @Column(nullable = false)
    private int length;
    @NotNull
    @Column(name="is_active", nullable = false)
    private boolean isActive;


    public CatalogueSku() {}

    public CatalogueSku(String sku_name, SkuCategory sku_category,
                       int _volume, int _weight, int _height, int _width, int _length, boolean _isActive)
    {
        skuName = sku_name;
        skuCategory = sku_category;
        volume = _volume;
        weight = _weight;
        height = _height;
        width = _width;
        length = _length;
        isActive = _isActive;
    }

    public CatalogueSku(Integer id, String sku_name, SkuCategory sku_category,
                        int _volume, int _weight, int _height, int _width, int _length, boolean _isActive)
    {
        skuCode=id;
        skuName = sku_name;
        skuCategory = sku_category;
        volume = _volume;
        weight = _weight;
        height = _height;
        width = _width;
        length = _length;
        isActive = _isActive;
    }

    // getters
    public Integer getSkuCode() {return skuCode;}
    public String getSkuName() {return skuName;}
    public SkuCategory getSkuCategory() {return skuCategory;}
    public int getVolume() {return volume;}
    public int getWeight() {return weight;}
    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public int getLength() {return length;}
    public boolean getIsActive() {return isActive;}

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
    public void setSkuCategory(SkuCategory skuCategory) {
        this.skuCategory = skuCategory;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void deactivate() {
        this.isActive = false;
    }
}

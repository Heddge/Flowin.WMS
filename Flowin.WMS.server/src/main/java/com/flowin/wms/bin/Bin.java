package com.flowin.wms.bin;

import com.flowin.wms.shelf.Shelf;
import com.flowin.wms.storageplace.StoragePlace;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "bins")
public class Bin
{
    @Id
    @Column(name = "bin_id")
    Long binId;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id")
    @MapsId
    StoragePlace storagePlace;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelf_id")
    Shelf shelf;

    @NotNull
    @Column(name = "last_used_at")
    Instant lastUsedAt;

    @NotNull
    @Min(value = 0)
    @Column(name = "occupied_volume")
    Integer occupiedVolume;
    @NotNull
    @Min(value = 0)
    @Column(name = "max_volume")
    Integer maxVolume;
    @NotNull
    @Min(value = 0)
    @Column(name = "occupied_weight")
    Integer occupiedWeight;
    @NotNull
    @Min(value = 0)
    @Column(name = "max_weight")
    Integer maxWeight;
    @NotNull
    @Min(value = 0)
    @Column(name = "occupied_count")
    Integer occupiedCount;
    @NotNull
    @Min(value = 0)
    @Column(name = "max_count")
    Integer maxCount;


    public Bin() {}

    public Bin(StoragePlace _storPlace, Shelf _shelf, Instant _lastUsedAt,
               Integer occVolume, Integer mVolume, Integer occWeight, Integer mWeight,
               Integer occCount, Integer mCount)
    {
        storagePlace = _storPlace;
        shelf = _shelf;
        lastUsedAt = _lastUsedAt;
        occupiedVolume = occVolume;
        maxVolume = mVolume;
        occupiedCount=occCount;
        maxCount=mCount;
        occupiedWeight = occWeight;
        maxWeight=mWeight;
    }

    public Long getBinId() {
        return binId;
    }

    public StoragePlace getStoragePlace() {
        return storagePlace;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public Instant getLastUsedAt() {
        return lastUsedAt;
    }

    public Integer getOccupiedVolume() {
        return occupiedVolume;
    }

    public Integer getMaxVolume() {
        return maxVolume;
    }

    public Integer getOccupiedWeight() {
        return occupiedWeight;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public Integer getOccupiedCount() {
        return occupiedCount;
    }

    public Integer getMaxCount() {
        return maxCount;
    }
}

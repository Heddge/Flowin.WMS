package com.flowin.wms.location;

import com.flowin.wms.storageplace.StoragePlace;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @Column(name = "location_id")
    private Long locationId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @MapsId
    private StoragePlace storagePlace;

    @NotNull
    @NotBlank
    @NotEmpty
    private String address;

    public Location() {}

    public Location(StoragePlace _storagePlace, String _address)
    {
        storagePlace = _storagePlace;
//        locationId = storagePlace.getId().intValue();
        address = _address;
    }

    public Long getLocationId() {
        return locationId;
    }

    public StoragePlace getStoragePlace() {
        return storagePlace;
    }

    public String getAddress() {
        return address;
    }
}

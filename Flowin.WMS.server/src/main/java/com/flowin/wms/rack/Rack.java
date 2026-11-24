package com.flowin.wms.rack;

import com.flowin.wms.zone.Zone;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "racks")
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer rackId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    Zone zone;
    @NotNull
    Boolean isActive;

    public Rack() {}

    public Rack(Zone _zone, Boolean _isActive)
    {
        zone=_zone;
        isActive = _isActive;
    }

    public Integer getRackId() {
        return rackId;
    }

    public Zone getZone() {
        return zone;
    }

    public Boolean getActive() {
        return isActive;
    }
}

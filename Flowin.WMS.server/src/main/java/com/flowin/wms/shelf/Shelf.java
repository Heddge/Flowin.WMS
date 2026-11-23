package com.flowin.wms.shelf;

import com.flowin.wms.rack.Rack;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "shelves")
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Min(value = 1)
    Integer shelfId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id")
    Rack rack;
    @NotNull
    Boolean isActive;

    public Shelf() {}

    public Shelf(Rack _rack, Boolean _isActive)
    {
        rack = _rack;
        isActive = _isActive;
    }

    public Integer getShelfId() {
        return shelfId;
    }

    public Rack getRack() {
        return rack;
    }

    public Boolean getActive() {
        return isActive;
    }
}

package com.flowin.wms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "work_areas")
public class WorkArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer workAreaId;
    @Column(nullable = false)
    String workAreaName;
    @Column(nullable = false, name = "is_active")
    Boolean isActive;

    public WorkArea() {}

    public WorkArea(String _name, Boolean _isActive)
    {
        workAreaName = _name;
        isActive = _isActive;
    }

    public Integer getId() {return workAreaId;}
    public String getName() {return workAreaName;}
    public Boolean getIsActive() {return isActive;}
}

package com.flowin.wms.workarea;

import jakarta.persistence.*;

@Entity
@Table(name = "work_areas")
public class WorkArea {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long workAreaId;
    @Column(nullable = false)
    String workAreaName;
    @Column(nullable = false, name = "is_active")
    Boolean isActive;

    public WorkArea() {}

    public WorkArea(Long id, String _name, Boolean _isActive)
    {
        workAreaId = id;
        workAreaName = _name;
        isActive = _isActive;
    }

    public Long getId() {return workAreaId;}
    public String getName() {return workAreaName;}
    public Boolean getIsActive() {return isActive;}
}

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

    public WorkArea() {}

    public WorkArea(String _name)
    {
        workAreaName = _name;
    }

    public Integer getId() {return workAreaId;}
    public String getName() {return workAreaName;}
}

package com.flowin.wms.workarea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkAreaRepository extends JpaRepository<WorkArea, Integer> {
    public WorkArea findByWorkAreaName(String name);

    @Query("select wa.isActive from WorkArea wa where wa.workAreaId = :id")
    public boolean getIsActiveById(Integer id);

    @Query("select count(*) from Zone z join Area a on z.zoneId = a.areaId where z.zoneId = :id")
    public Long getCountForeignersById(Integer id);

    @NativeQuery("select nextval('storage_work_area_id_seq')")
    public Long getNewId();
}

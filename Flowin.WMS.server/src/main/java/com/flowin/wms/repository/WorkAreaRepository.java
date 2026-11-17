package com.flowin.wms.repository;

import com.flowin.wms.entity.WorkArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkAreaRepository extends JpaRepository<WorkArea, Integer> {
    public WorkArea findByWorkAreaName(String name);
}

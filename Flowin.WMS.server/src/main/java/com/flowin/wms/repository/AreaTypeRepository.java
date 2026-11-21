package com.flowin.wms.repository;

import com.flowin.wms.entity.AreaType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaTypeRepository extends JpaRepository<AreaType, Integer> {
    public AreaType findByAreaTypeName(String name);
}

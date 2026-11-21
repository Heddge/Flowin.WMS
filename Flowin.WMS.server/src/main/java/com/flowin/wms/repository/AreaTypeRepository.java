package com.flowin.wms.repository;

import com.flowin.wms.entity.AreaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaTypeRepository extends JpaRepository<AreaType, Integer> {
    public Optional<AreaType> findByAreaTypeName(String name);
}

package com.flowin.wms.repository;

import com.flowin.wms.entity.SkuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkuCategoryRepository extends JpaRepository<SkuCategory, Integer>
{
    public Optional<SkuCategory> findBySkuCategoryName(String name);
}

package com.flowin.wms.skucategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkuCategoryRepository extends JpaRepository<SkuCategory, Integer>
{
    public SkuCategory findBySkuCategoryName(String name);
}

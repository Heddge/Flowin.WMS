package com.flowin.wms.skucategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SkuCategoryRepository extends JpaRepository<SkuCategory, Integer>
{
    public SkuCategory findBySkuCategoryName(String name);
}

package com.flowin.wms.repository;

import com.flowin.wms.entity.CatalogueSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueSkuRepository extends JpaRepository<CatalogueSku, Integer>
{
}

package com.flowin.wms.cataloguesku;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueSkuRepository extends JpaRepository<CatalogueSku, Integer>
{
}

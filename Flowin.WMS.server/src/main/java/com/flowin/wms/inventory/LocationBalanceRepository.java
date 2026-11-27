package com.flowin.wms.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationBalanceRepository extends JpaRepository<LocationBalance, LocationBalanceId> {
    @Query("select lb from LocationBalance lb left join fetch lb.sku left join fetch lb.location where lb.location.locationId = :id")
    public LocationBalance getBinInventory(Integer id);
}

package com.flowin.wms.inventory;

import com.flowin.wms.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationBalanceRepository extends JpaRepository<LocationBalance, LocationBalanceId> {
    @Query("select lb from LocationBalance lb left join fetch lb.sku left join fetch lb.location where lb.location.locationId = :id")
    List<LocationBalance> getLocationInventory(Integer id);

    @Query("select lb from LocationBalance lb where lb.location.locationId = :id")
    LocationBalance getByLocationId(Integer id);

    LocationBalance getByLocationBalanceId(LocationBalanceId id);

    Boolean existsByLocationBalanceId(LocationBalanceId locationBalanceId);
}

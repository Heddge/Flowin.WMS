package com.flowin.wms.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationBalanceRepository extends JpaRepository<LocationBalance, LocationBalanceId> {
}

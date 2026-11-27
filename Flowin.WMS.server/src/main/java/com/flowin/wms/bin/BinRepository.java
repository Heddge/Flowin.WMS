package com.flowin.wms.bin;

import com.flowin.wms.storageplace.StoragePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BinRepository extends JpaRepository<Bin, Integer > {
    public Boolean existsByBinId(Integer id);

    @Query("select b.storagePlace from Bin b where b.binId = :binId")
    public StoragePlace getStoragePlace(Integer binId);
}

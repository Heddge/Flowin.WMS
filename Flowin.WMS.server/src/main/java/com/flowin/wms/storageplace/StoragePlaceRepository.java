package com.flowin.wms.storageplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoragePlaceRepository extends JpaRepository<StoragePlace, Integer> {
    @Query("select s.isActive from StoragePlace s where s.Id = :id")
    public boolean getIsActiveById(Integer id);
}

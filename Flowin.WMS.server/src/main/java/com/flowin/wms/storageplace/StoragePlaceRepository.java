package com.flowin.wms.storageplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;

@Repository
public interface StoragePlaceRepository extends JpaRepository<StoragePlace, Integer> {
    @Query("select s.isActive from StoragePlace s where s.Id = :id")
    public boolean getIsActiveById(Integer id);


    @NativeQuery("select nextval('storage_work_area_id_seq')")
    public Long getNewId();
}

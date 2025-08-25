package com.project.management.repository;

import com.project.management.entity.DzongkhagLookup;
import com.project.management.entity.GewogLookup;
import com.project.management.entity.VillageLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VillageLookupRepository extends JpaRepository<VillageLookup, Integer> {
    List<VillageLookup> findAll();

    @Query("SELECT v FROM VillageLookup v WHERE v.gewog.gewogSerialNo = :gewogSerialNo")
    List<VillageLookup> findByGewogSerialNo(@Param("gewogSerialNo") Integer gewogSerialNo);

    @Query("SELECT v.gewog.dzongkhag FROM VillageLookup v WHERE v.villageSerialNo = :villageSerialNo")
    DzongkhagLookup findDzongkhagByVillageSerialNo(@Param("villageSerialNo") Integer villageSerialNo);

    @Query("SELECT v.gewog FROM VillageLookup v WHERE v.villageSerialNo = :villageSerialNo")
    GewogLookup findGewogByVillageSerialNo(@Param("villageSerialNo") Integer villageSerialNo);
}
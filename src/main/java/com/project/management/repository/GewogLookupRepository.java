package com.project.management.repository;

import com.project.management.entity.GewogLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GewogLookupRepository extends JpaRepository<GewogLookup, Integer> {

    List<GewogLookup> findAll();

    @Query("SELECT g FROM GewogLookup g WHERE g.dzongkhag.dzongkhagSerialNo = :dzongkhagSerialNo")
    List<GewogLookup> findByDzongkhagSerialNo(@Param("dzongkhagSerialNo") Integer dzongkhagSerialNo);

    Optional<GewogLookup> findByGewogId(String gewogId);
}
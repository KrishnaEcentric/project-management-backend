package com.project.management.repository;

import com.project.management.entity.DzongkhagLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DzongkhagLookupRepository extends JpaRepository<DzongkhagLookup, Integer> {
    List<DzongkhagLookup> findAll();
}
package com.project.management.repository;

import com.project.management.entity.QualificationLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface QualificationLookupRepository extends JpaRepository<QualificationLookup, Integer> {
    Optional<QualificationLookup> findByQualificationId(Integer qualificationId);
    boolean existsByQualificationId(Integer qualificationId);
}
package com.project.management.repository;

import com.project.management.entity.RoleLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleLookupRepository extends JpaRepository<RoleLookup, Integer> {

    Optional<RoleLookup> findByRoleId(Integer roleId);

    Optional<RoleLookup> findByRoleName(String roleName);

    List<RoleLookup> findAllByOrderByRoleIdAsc();

    @Query("SELECT r FROM RoleLookup r WHERE r.roleId = :roleId")
    Optional<RoleLookup> findRoleByRoleId(@Param("roleId") Integer roleId);
}
package com.project.management.repository;

import com.project.management.entity.UserLoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserLoginDetailsRepository extends JpaRepository<UserLoginDetails, Integer> {

    Optional<UserLoginDetails> findByEmail(String email);
    Optional<UserLoginDetails> findByUserId(String userId);
    Optional<UserLoginDetails> findByEmailOrUserId(String email, String userId);
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);
}
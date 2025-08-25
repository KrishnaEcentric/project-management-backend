package com.project.management.repository;

import com.project.management.entity.UserRegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRegisterDetailsRepository extends JpaRepository<UserRegisterDetails, Integer> {
    Optional<UserRegisterDetails> findByEmail(String email);
    Optional<UserRegisterDetails> findByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);
}
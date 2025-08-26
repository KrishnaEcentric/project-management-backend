package com.project.management.repository;

import com.project.management.entity.UserRegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRegisterDetailsRepository extends JpaRepository<UserRegisterDetails, Integer> {
    Optional<UserRegisterDetails> findByEmail(String email);
    Optional<UserRegisterDetails> findByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);

    @Modifying
    @Query("UPDATE UserRegisterDetails u SET u.photoUrl = :photoUrl WHERE u.userId = :userId")
    void updatePhotoUrlByUserId(@Param("userId") String userId, @Param("photoUrl") String photoUrl);
}
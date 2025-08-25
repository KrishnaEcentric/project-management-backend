package com.project.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_user_login_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userid", nullable = false, length = 50)
    private String userId;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    public UserLoginDetails(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    @PrePersist
    protected void onCreate() {
        lastLogin = LocalDateTime.now();
    }
}
package com.project.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_user_register_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstName;

    @Column(name = "middlename", length = 100)
    private String middleName;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "userid", nullable = false, length = 50, unique = true)
    private String userId;

    @Column(name = "roleid", nullable = false)
    private Integer roleId;

    @Column(name = "presentaddress", columnDefinition = "TEXT")
    private String presentAddress;

    @Column(name = "mobileno", length = 20)
    private String mobileNo;

    @Column(name = "photourl", columnDefinition = "TEXT")
    private String photoUrl;

    @Column(name = "createdby", length = 20)
    private String createdBy;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updatedby", length = 20)
    private String updatedBy;

    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    public UserRegisterDetails(String firstName, String lastName, String email, String userId, Integer roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userId = userId;
        this.roleId = roleId;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
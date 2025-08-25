package com.project.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_qualification_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QualificationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "qualificationid", nullable = false, length = 50, unique = true)
    private String qualificationId;

    @Column(name = "userid", nullable = false, length = 50)
    private String userId;

    @Column(name = "qualification_name", nullable = false, length = 255)
    private String qualificationName;

    @Column(name = "institution", nullable = false, length = 255)
    private String institution;

    @Column(name = "year_completed")
    private Integer yearCompleted;

    @Column(name = "createdby", length = 20)
    private String createdBy;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updatedby", length = 20)
    private String updatedBy;

    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    public QualificationDetails(String qualificationId, String userId, String qualificationName, String institution) {
        this.qualificationId = qualificationId;
        this.userId = userId;
        this.qualificationName = qualificationName;
        this.institution = institution;
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
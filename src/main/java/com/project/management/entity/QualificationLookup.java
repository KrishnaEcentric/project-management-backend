package com.project.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_qualification_lookup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QualificationLookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "qualificationid", nullable = false, unique = true)
    private Integer qualificationId;

    @Column(name = "qualification_name", nullable = false, length = 255)
    private String qualificationName;

    @Column(name = "createdby", length = 20)
    private String createdBy;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updatedby", length = 20)
    private String updatedBy;

    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    // Constructor without id (for creating new entities)
    public QualificationLookup(Integer qualificationId, String qualificationName,
                               String createdBy, String updatedBy) {
        this.qualificationId = qualificationId;
        this.qualificationName = qualificationName;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
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
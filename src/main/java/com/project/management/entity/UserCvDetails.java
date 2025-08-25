package com.project.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_user_cv_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCvDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userid", nullable = false, length = 50)
    private String userId;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "villageid")
    private VillageLookup village;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gewogid")
    private GewogLookup gewog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dzongkhagid")
    private DzongkhagLookup dzongkhag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualificationid")
    private QualificationDetails qualification;

    @Column(name = "interest", columnDefinition = "TEXT")
    private String interest;

    @Column(name = "hobbies", columnDefinition = "TEXT")
    private String hobbies;

    @Column(name = "languagewrittenspoken", columnDefinition = "TEXT")
    private String languagesWrittenSpoken;

    @Column(name = "createdby", length = 20)
    private String createdBy;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updatedby", length = 20)
    private String updatedBy;

    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    public UserCvDetails(String userId) {
        this.userId = userId;
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
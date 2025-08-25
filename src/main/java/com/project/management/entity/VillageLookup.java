package com.project.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_village_lookup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VillageLookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "village_serial_no")
    private Integer villageSerialNo;

    @Column(name = "village_id", nullable = false, length = 20, unique = true)
    private String villageId;

    @Column(name = "village_name", nullable = false, length = 200)
    private String villageName;

    @Column(name = "village_name_bh", length = 200)
    private String villageNameBh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gewog_serial_no", nullable = false)
    private GewogLookup gewog;

    @Column(name = "chiwog_serial_no", nullable = false)
    private Integer chiwogSerialNo;

    @Column(name = "status", length = 12)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public VillageLookup(String villageId, String villageName, String villageNameBh, GewogLookup gewog, Integer chiwogSerialNo) {
        this.villageId = villageId;
        this.villageName = villageName;
        this.villageNameBh = villageNameBh;
        this.gewog = gewog;
        this.chiwogSerialNo = chiwogSerialNo;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
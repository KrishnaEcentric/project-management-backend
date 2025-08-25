package com.project.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_gewog_lookup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GewogLookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gewog_serial_no")
    private Integer gewogSerialNo;

    @Column(name = "gewog_id", nullable = false, length = 20, unique = true)
    private String gewogId;

    @Column(name = "gewog_name", nullable = false, length = 150)
    private String gewogName;

    @Column(name = "gewog_name_bh", length = 150)
    private String gewogNameBh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dzongkhag_serial_no", nullable = false)
    private DzongkhagLookup dzongkhag;

    @Column(name = "dungkhag_serial_no")
    private Integer dungkhagSerialNo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public GewogLookup(String gewogId, String gewogName, String gewogNameBh, DzongkhagLookup dzongkhag) {
        this.gewogId = gewogId;
        this.gewogName = gewogName;
        this.gewogNameBh = gewogNameBh;
        this.dzongkhag = dzongkhag;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
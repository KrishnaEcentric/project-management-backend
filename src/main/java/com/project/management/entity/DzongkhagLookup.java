package com.project.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_dzongkhag_lookup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DzongkhagLookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dzongkhag_serial_no")
    private Integer dzongkhagSerialNo;

    @Column(name = "dzongkhag_id", nullable = false, length = 20, unique = true)
    private String dzongkhagId;

    @Column(name = "dzongkhag_name", nullable = false, length = 150)
    private String dzongkhagName;

    @Column(name = "dzongkhag_name_bh", length = 150)
    private String dzongkhagNameBh;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructor without serialNo (for creating new entities)
    public DzongkhagLookup(String dzongkhagId, String dzongkhagName, String dzongkhagNameBh) {
        this.dzongkhagId = dzongkhagId;
        this.dzongkhagName = dzongkhagName;
        this.dzongkhagNameBh = dzongkhagNameBh;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
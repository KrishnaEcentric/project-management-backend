package com.project.management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VillageDTO {
    private Integer villageSerialNo;
    private String villageId;
    private String villageName;
    private String villageNameBh;
    private GewogDTO gewog;
    private Integer chiwogSerialNo;
    private String status;
    private LocalDateTime createdAt;
}
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
public class DzongkhagDTO {
    private Integer dzongkhagSerialNo;
    private String dzongkhagId;
    private String dzongkhagName;
    private String dzongkhagNameBh;
    private LocalDateTime createdAt;
}
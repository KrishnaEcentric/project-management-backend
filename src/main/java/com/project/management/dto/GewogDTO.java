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
public class GewogDTO {
    private Integer gewogSerialNo;
    private String gewogId;
    private String gewogName;
    private String gewogNameBh;
    private DzongkhagDTO dzongkhag;
    private Integer dungkhagSerialNo;
    private LocalDateTime createdAt;
}
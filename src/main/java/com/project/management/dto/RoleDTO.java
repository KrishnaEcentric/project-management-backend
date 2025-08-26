package com.project.management.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RoleDTO {
    private Integer id;
    private Integer roleId;
    private String roleName;
    private LocalDate createdAt;
}
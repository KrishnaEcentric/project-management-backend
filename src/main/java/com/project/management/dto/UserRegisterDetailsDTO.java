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
public class UserRegisterDetailsDTO {
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String userId;
    private Integer roleId;
    private String presentAddress;
    private String mobileNo;
    private String photoUrl;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}
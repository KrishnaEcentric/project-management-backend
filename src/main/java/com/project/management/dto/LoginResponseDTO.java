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
public class LoginResponseDTO {

    private Integer id;
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private Integer roleId;
    private LocalDateTime lastLogin;
    private String token;
    private String message;
}
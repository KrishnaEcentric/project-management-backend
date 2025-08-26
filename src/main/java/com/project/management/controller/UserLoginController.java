package com.project.management.controller;

import com.project.management.dto.ApproveRequestDTO;
import com.project.management.dto.LoginRequestDTO;
import com.project.management.dto.LoginResponseDTO;
import com.project.management.dto.PasswordUpdateDTO;
import com.project.management.service.UserLoginDetailsService;
import com.project.management.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API", description = "APIs for user authentication and password management")
public class UserLoginController {

    @Autowired
    private UserLoginDetailsService userLoginDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Operation(summary = "Approve user", description = "Approve a registered user and generate default password")
    @PostMapping("/approve")
    public ResponseEntity<?> approveUser(@Valid @RequestBody ApproveRequestDTO approveRequest) {
        try {
            LoginResponseDTO response = userLoginDetailsService.approveUser(approveRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error approving user: " + e.getMessage());
        }
    }

    @Operation(summary = "User login", description = "Authenticate user with email/userId and password")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = userLoginDetailsService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during login: " + e.getMessage());
        }
    }

    @Operation(summary = "Update password", description = "Update user password with validation")
    @PutMapping("/{username}/password")
    public ResponseEntity<?> updatePassword(
            @PathVariable String username,
            @Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        try {
            LoginResponseDTO response = userLoginDetailsService.updatePassword(username, passwordUpdateDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating password: " + e.getMessage());
        }
    }

    @Operation(summary = "Validate token", description = "Validate JWT token")
    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        try {
            boolean isValid = jwtTokenUtil.validateToken(token);
            if (isValid) {
                return ResponseEntity.ok().body("Token is valid");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error validating token: " + e.getMessage());
        }
    }
}
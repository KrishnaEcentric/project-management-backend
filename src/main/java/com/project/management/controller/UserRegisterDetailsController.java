package com.project.management.controller;

import com.project.management.dto.UserRegisterDetailsDTO;
import com.project.management.service.UserRegisterDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management API", description = "APIs for managing user registration details")
public class UserRegisterDetailsController {

    @Autowired
    private UserRegisterDetailsService userRegisterDetailsService;

    @Operation(summary = "Create new user", description = "Register a new user with the provided details")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRegisterDetailsDTO userDTO) {
        try {
            UserRegisterDetailsDTO createdUser = userRegisterDetailsService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user: " + e.getMessage());
        }
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    @GetMapping
    public ResponseEntity<List<UserRegisterDetailsDTO>> getAllUsers() {
        List<UserRegisterDetailsDTO> users = userRegisterDetailsService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Get user by ID", description = "Retrieve user details by user ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserRegisterDetailsDTO> getUserById(@PathVariable Integer id) {
        UserRegisterDetailsDTO user = userRegisterDetailsService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get user by email", description = "Retrieve user details by email address")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserRegisterDetailsDTO> getUserByEmail(@PathVariable String email) {
        UserRegisterDetailsDTO user = userRegisterDetailsService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get user by user ID", description = "Retrieve user details by user ID")
    @GetMapping("/userid/{userId}")
    public ResponseEntity<UserRegisterDetailsDTO> getUserByUserId(@PathVariable String userId) {
        UserRegisterDetailsDTO user = userRegisterDetailsService.getUserByUserId(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update user", description = "Update existing user details")
    @PutMapping("/{id}")
    public ResponseEntity<UserRegisterDetailsDTO> updateUser(
            @PathVariable Integer id,
            @RequestBody UserRegisterDetailsDTO userDTO) {
        UserRegisterDetailsDTO updatedUser = userRegisterDetailsService.updateUser(id, userDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete user", description = "Delete a user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userRegisterDetailsService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
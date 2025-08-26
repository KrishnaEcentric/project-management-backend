package com.project.management.controller;

import com.project.management.dto.UserRegisterDetailsDTO;
import com.project.management.service.UserRegisterDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Registration API", description = "APIs for managing user registration details")
public class UserRegisterDetailsController {

    @Autowired
    private UserRegisterDetailsService userRegisterDetailsService;

    @Operation(summary = "Create new user", description = "Register a new user with the provided details")
    @PostMapping("/registerNewUser")
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
    @GetMapping("/getAllUsers")
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

    @Operation(summary = "Add user photo", description = "Upload and store user photo")
    @PostMapping(value = "/{userId}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addUserPhoto(
            @PathVariable String userId,
            @RequestParam("file") MultipartFile file) {
        try {
            String photoUrl = userRegisterDetailsService.addUserPhoto(userId, file);
            return ResponseEntity.ok().body("{\"photoUrl\": \"" + photoUrl + "\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading photo: " + e.getMessage());
        }
    }

    @Operation(summary = "Get user photo", description = "Retrieve user photo by filename")
    @GetMapping(value = "/photo/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable String fileName) {
        try {
            byte[] photoData = userRegisterDetailsService.getUserPhoto(fileName);
            return ResponseEntity.ok().body(photoData);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get user photo by userId", description = "Retrieve user photo by userId")
    @GetMapping(value = "/{userId}/photo", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getUserPhotoByUserId(@PathVariable String userId) {
        try {
            byte[] photoData = userRegisterDetailsService.getUserPhotoByUserId(userId);
            return ResponseEntity.ok().body(photoData);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
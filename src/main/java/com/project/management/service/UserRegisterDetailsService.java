package com.project.management.service;

import com.project.management.dto.UserRegisterDetailsDTO;
import com.project.management.entity.UserRegisterDetails;
import com.project.management.repository.UserRegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRegisterDetailsService {

    @Autowired
    private UserRegisterDetailsRepository userRegisterDetailsRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public UserRegisterDetailsDTO createUser(UserRegisterDetailsDTO userDTO) {
        // Check if email or userId already exists
        if (userRegisterDetailsRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists: " + userDTO.getEmail());
        }

        if (userRegisterDetailsRepository.existsByUserId(userDTO.getUserId())) {
            throw new RuntimeException("User ID already exists: " + userDTO.getUserId());
        }

        UserRegisterDetails user = convertToEntity(userDTO);
        UserRegisterDetails savedUser = userRegisterDetailsRepository.save(user);
        return convertToDTO(savedUser);
    }

    public List<UserRegisterDetailsDTO> getAllUsers() {
        List<UserRegisterDetails> users = userRegisterDetailsRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserRegisterDetailsDTO getUserById(Integer id) {
        return userRegisterDetailsRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public UserRegisterDetailsDTO getUserByEmail(String email) {
        return userRegisterDetailsRepository.findByEmail(email)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public UserRegisterDetailsDTO getUserByUserId(String userId) {
        return userRegisterDetailsRepository.findByUserId(userId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public UserRegisterDetailsDTO updateUser(Integer id, UserRegisterDetailsDTO userDTO) {
        return userRegisterDetailsRepository.findById(id)
                .map(existingUser -> {
                    updateEntityFromDTO(existingUser, userDTO);
                    existingUser.setUpdatedAt(LocalDateTime.now());
                    UserRegisterDetails updatedUser = userRegisterDetailsRepository.save(existingUser);
                    return convertToDTO(updatedUser);
                })
                .orElse(null);
    }

    public void deleteUser(Integer id) {
        userRegisterDetailsRepository.deleteById(id);
    }

    private UserRegisterDetails convertToEntity(UserRegisterDetailsDTO dto) {
        UserRegisterDetails user = new UserRegisterDetails();
        user.setFirstName(dto.getFirstName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setUserId(dto.getUserId());
        user.setRoleId(dto.getRoleId());
        user.setPresentAddress(dto.getPresentAddress());
        user.setMobileNo(dto.getMobileNo());
        user.setPhotoUrl(dto.getPhotoUrl());
        user.setCreatedBy(dto.getCreatedBy());
        user.setUpdatedBy(dto.getUpdatedBy());
        return user;
    }

    private void updateEntityFromDTO(UserRegisterDetails user, UserRegisterDetailsDTO dto) {
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getMiddleName() != null) user.setMiddleName(dto.getMiddleName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getUserId() != null) user.setUserId(dto.getUserId());
        if (dto.getRoleId() != null) user.setRoleId(dto.getRoleId());
        if (dto.getPresentAddress() != null) user.setPresentAddress(dto.getPresentAddress());
        if (dto.getMobileNo() != null) user.setMobileNo(dto.getMobileNo());
        if (dto.getPhotoUrl() != null) user.setPhotoUrl(dto.getPhotoUrl());
        if (dto.getUpdatedBy() != null) user.setUpdatedBy(dto.getUpdatedBy());
    }

    private UserRegisterDetailsDTO convertToDTO(UserRegisterDetails user) {
        return new UserRegisterDetailsDTO(
                user.getId(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getEmail(),
                user.getUserId(),
                user.getRoleId(),
                user.getPresentAddress(),
                user.getMobileNo(),
                user.getPhotoUrl(),
                user.getCreatedBy(),
                user.getCreatedAt(),
                user.getUpdatedBy(),
                user.getUpdatedAt()
        );
    }

    public String addUserPhoto(String userId, MultipartFile file) {
        try {
            // Check if user exists
            UserRegisterDetails user = userRegisterDetailsRepository.findByUserId(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));

            // Store file and get filename
            String fileName = fileStorageService.storeFile(file, userId);

            // Build photo URL (you can customize this based on your needs)
            String photoUrl = "/api/users/photo/" + fileName;

            // Update user's photo URL
            user.setPhotoUrl(photoUrl);
            userRegisterDetailsRepository.save(user);

            return photoUrl;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }
    }

    public byte[] getUserPhoto(String fileName) {
        try {
            return fileStorageService.loadFile(fileName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load file: " + e.getMessage());
        }
    }

    public byte[] getUserPhotoByUserId(String userId) {
        UserRegisterDetails user = userRegisterDetailsRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));

        if (user.getPhotoUrl() == null) {
            throw new RuntimeException("No photo found for user: " + userId);
        }

        // Extract filename from URL
        String fileName = user.getPhotoUrl().substring(user.getPhotoUrl().lastIndexOf("/") + 1);
        return getUserPhoto(fileName);
    }
}
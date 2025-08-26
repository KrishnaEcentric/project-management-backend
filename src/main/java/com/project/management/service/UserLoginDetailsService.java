package com.project.management.service;

import com.project.management.dto.ApproveRequestDTO;
import com.project.management.dto.LoginRequestDTO;
import com.project.management.dto.LoginResponseDTO;
import com.project.management.dto.PasswordUpdateDTO;
import com.project.management.entity.UserLoginDetails;
import com.project.management.entity.UserRegisterDetails;
import com.project.management.repository.UserLoginDetailsRepository;
import com.project.management.repository.UserRegisterDetailsRepository;
import com.project.management.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserLoginDetailsService {

    @Autowired
    private UserLoginDetailsRepository userLoginDetailsRepository;

    @Autowired
    private UserRegisterDetailsRepository userRegisterDetailsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static final String DEFAULT_PASSWORD = "ecentric123";

    @Transactional
    public LoginResponseDTO approveUser(ApproveRequestDTO approveRequest) {
        // Find the user in registration table
        UserRegisterDetails registerDetails = null;

        if (approveRequest.getEmail() != null && !approveRequest.getEmail().isEmpty()) {
            registerDetails = userRegisterDetailsRepository.findByEmail(approveRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found with email: " + approveRequest.getEmail()));
        } else if (approveRequest.getUserId() != null && !approveRequest.getUserId().isEmpty()) {
            registerDetails = userRegisterDetailsRepository.findByUserId(approveRequest.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with userId: " + approveRequest.getUserId()));
        } else {
            throw new RuntimeException("Either email or userId must be provided");
        }

        // Check if user already exists in login table
        if (userLoginDetailsRepository.existsByEmail(registerDetails.getEmail()) ||
                userLoginDetailsRepository.existsByUserId(registerDetails.getUserId())) {
            throw new RuntimeException("User is already approved");
        }

        // Create login details with encrypted default password
        String encryptedPassword = passwordEncoder.encode(DEFAULT_PASSWORD);
        UserLoginDetails loginDetails = new UserLoginDetails(
                registerDetails.getUserId(),
                registerDetails.getEmail(),
                encryptedPassword
        );

        // Save to login table
        UserLoginDetails savedLoginDetails = userLoginDetailsRepository.save(loginDetails);

        // Generate token for approved user
        String token = jwtTokenUtil.generateToken(
                registerDetails.getEmail(),
                registerDetails.getUserId(),
                registerDetails.getRoleId()
        );
        // Prepare response
        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(savedLoginDetails.getId());
        response.setUserId(savedLoginDetails.getUserId());
        response.setEmail(savedLoginDetails.getEmail());
        response.setFirstName(registerDetails.getFirstName());
        response.setLastName(registerDetails.getLastName());
        response.setRoleId(registerDetails.getRoleId());
        response.setLastLogin(savedLoginDetails.getLastLogin());
        response.setMessage("User approved successfully. Default password: " + DEFAULT_PASSWORD);
        response.setToken(token);

        return response;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        // Find user by email or userId
        Optional<UserLoginDetails> loginDetailsOpt = userLoginDetailsRepository
                .findByEmailOrUserId(loginRequest.getUsername(), loginRequest.getUsername());

        if (loginDetailsOpt.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        UserLoginDetails loginDetails = loginDetailsOpt.get();

        // Verify password
        if (!passwordEncoder.matches(loginRequest.getPassword(), loginDetails.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Get user details from registration table
        UserRegisterDetails registerDetails = userRegisterDetailsRepository.findByEmail(loginDetails.getEmail())
                .orElseThrow(() -> new RuntimeException("User registration details not found"));

        // Update last login time
        loginDetails.setLastLogin(LocalDateTime.now());
        userLoginDetailsRepository.save(loginDetails);

        // Generate new token for each login
        String token = jwtTokenUtil.generateToken(
                loginDetails.getEmail(),
                loginDetails.getUserId(),
                registerDetails.getRoleId()
        );

        // Prepare response
        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(loginDetails.getId());
        response.setUserId(loginDetails.getUserId());
        response.setEmail(loginDetails.getEmail());
        response.setFirstName(registerDetails.getFirstName());
        response.setLastName(registerDetails.getLastName());
        response.setRoleId(registerDetails.getRoleId());
        response.setLastLogin(loginDetails.getLastLogin());
        response.setMessage("Login successful");
        response.setToken(token);

        return response;
    }

    @Transactional
    public LoginResponseDTO updatePassword(String username, PasswordUpdateDTO passwordUpdateDTO) {
        // Find user by email or userId
        Optional<UserLoginDetails> loginDetailsOpt = userLoginDetailsRepository
                .findByEmailOrUserId(username, username);

        if (loginDetailsOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        UserLoginDetails loginDetails = loginDetailsOpt.get();

        // Verify current password
        if (!passwordEncoder.matches(passwordUpdateDTO.getCurrentPassword(), loginDetails.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        // Check if new password matches confirmation
        if (!passwordUpdateDTO.getNewPassword().equals(passwordUpdateDTO.getConfirmPassword())) {
            throw new RuntimeException("New password and confirmation do not match");
        }

        // Update password
        String encryptedNewPassword = passwordEncoder.encode(passwordUpdateDTO.getNewPassword());
        loginDetails.setPassword(encryptedNewPassword);
        userLoginDetailsRepository.save(loginDetails);

        // Get user details from registration table
        UserRegisterDetails registerDetails = userRegisterDetailsRepository.findByEmail(loginDetails.getEmail())
                .orElseThrow(() -> new RuntimeException("User registration details not found"));

        // Generate new token after password update
        String token = jwtTokenUtil.generateToken(
                loginDetails.getEmail(),
                loginDetails.getUserId(),
                registerDetails.getRoleId()
        );

        // Prepare response
        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(loginDetails.getId());
        response.setUserId(loginDetails.getUserId());
        response.setEmail(loginDetails.getEmail());
        response.setFirstName(registerDetails.getFirstName());
        response.setLastName(registerDetails.getLastName());
        response.setRoleId(registerDetails.getRoleId());
        response.setLastLogin(loginDetails.getLastLogin());
        response.setMessage("Password updated successfully");
        response.setToken(token);

        return response;
    }
}
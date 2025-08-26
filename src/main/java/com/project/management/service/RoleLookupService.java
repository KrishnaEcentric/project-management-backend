package com.project.management.service;

import com.project.management.dto.RoleDTO;
import com.project.management.entity.RoleLookup;
import com.project.management.repository.RoleLookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleLookupService {

    @Autowired
    private RoleLookupRepository roleLookupRepository;

    public List<RoleDTO> getAllRoles() {
        List<RoleLookup> roles = roleLookupRepository.findAllByOrderByRoleIdAsc();
        return roles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RoleDTO getRoleByRoleId(Integer roleId) {
        Optional<RoleLookup> role = roleLookupRepository.findByRoleId(roleId);
        return role.map(this::convertToDTO).orElse(null);
    }

    private RoleDTO convertToDTO(RoleLookup role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setRoleId(role.getRoleId());
        dto.setRoleName(role.getRoleName());
        dto.setCreatedAt(role.getCreatedAt());
        return dto;
    }
}
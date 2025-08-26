package com.project.management.controller;

import com.project.management.dto.*;
import com.project.management.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lookup")
@Tag(name = "Lookup API", description = "APIs for all lookup data")
public class LookupController {

    @Autowired
    private DzongkhagLookupService dzongkhagLookupService;

    @Autowired
    private GewogLookupService gewogLookupService;

    @Autowired
    private VillageLookupService villageLookupService;

    @Autowired
    private RoleLookupService roleLookupService;

    @Autowired
    private QualificationLookupService qualificationLookupService;

    // 1. Get all dzongkhags
    @Operation(summary = "Get all dzongkhags", description = "Returns a list of all dzongkhags")
    @GetMapping("/dzongkhags")
    public ResponseEntity<List<DzongkhagDTO>> getAllDzongkhags() {
        List<DzongkhagDTO> dzongkhags = dzongkhagLookupService.getAllDzongkhags();
        return ResponseEntity.ok(dzongkhags);
    }

    // 3. Get dzongkhag by gewog serial number
    @Operation(summary = "Get dzongkhag by gewog serial number", description = "Returns dzongkhag for a specific gewog")
    @GetMapping("/dzongkhag/by-gewog/{gewogSerialNo}")
    public ResponseEntity<DzongkhagDTO> getDzongkhagByGewogSerialNo(@PathVariable Integer gewogSerialNo) {
        DzongkhagDTO dzongkhag = villageLookupService.getDzongkhagByGewogSerialNo(gewogSerialNo);
        if (dzongkhag != null) {
            return ResponseEntity.ok(dzongkhag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. Get gewog by village serial number
    @Operation(summary = "Get gewog by village serial number", description = "Returns gewog for a specific village")
    @GetMapping("/gewog/by-village/{villageSerialNo}")
    public ResponseEntity<GewogDTO> getGewogByVillageSerialNo(@PathVariable Integer villageSerialNo) {
        GewogDTO gewog = gewogLookupService.getGewogByVillageSerialNo(villageSerialNo);
        if (gewog != null) {
            return ResponseEntity.ok(gewog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Get gewogs by dzongkhag serial number
    @Operation(summary = "Get gewogs by dzongkhag", description = "Returns gewogs for a specific dzongkhag")
    @GetMapping("/gewogs/by-dzongkhag/{dzongkhagSerialNo}")
    public ResponseEntity<List<GewogDTO>> getGewogsByDzongkhag(@PathVariable Integer dzongkhagSerialNo) {
        List<GewogDTO> gewogs = gewogLookupService.getGewogsByDzongkhagSerialNo(dzongkhagSerialNo);
        return ResponseEntity.ok(gewogs);
    }

    // 6. Get villages by gewog serial number
    @Operation(summary = "Get villages by gewog", description = "Returns villages for a specific gewog")
    @GetMapping("/villages/by-gewog/{gewogSerialNo}")
    public ResponseEntity<List<VillageDTO>> getVillagesByGewog(@PathVariable Integer gewogSerialNo) {
        List<VillageDTO> villages = villageLookupService.getVillagesByGewogSerialNo(gewogSerialNo);
        return ResponseEntity.ok(villages);
    }

    // 7. Get dzongkhag by village serial number
    @Operation(summary = "Get dzongkhag by village serial number", description = "Returns dzongkhag for a specific village")
    @GetMapping("/dzongkhag/by-village/{villageSerialNo}")
    public ResponseEntity<DzongkhagDTO> getDzongkhagByVillageSerialNo(@PathVariable Integer villageSerialNo) {
        DzongkhagDTO dzongkhag = villageLookupService.getDzongkhagByVillageSerialNo(villageSerialNo);
        if (dzongkhag != null) {
            return ResponseEntity.ok(dzongkhag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 8. Get gewog by serial number
    @Operation(summary = "Get gewog by serial number", description = "Returns a specific gewog by its serial number")
    @GetMapping("/gewog/{gewogSerialNo}")
    public ResponseEntity<GewogDTO> getGewogBySerialNo(@PathVariable Integer gewogSerialNo) {
        GewogDTO gewog = gewogLookupService.getGewogById(gewogSerialNo);
        if (gewog != null) {
            return ResponseEntity.ok(gewog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 9. Get gewog by gewog ID
    @Operation(summary = "Get gewog by gewog ID", description = "Returns a specific gewog by its gewog ID")
    @GetMapping("/gewog/id/{gewogId}")
    public ResponseEntity<GewogDTO> getGewogByGewogId(@PathVariable String gewogId) {
        GewogDTO gewog = gewogLookupService.getGewogByGewogId(gewogId);
        if (gewog != null) {
            return ResponseEntity.ok(gewog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 10. Get all roles
    @Operation(summary = "Get all roles", description = "Returns a list of all roles")
    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleLookupService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    // 11. Get role by role ID
    @Operation(summary = "Get role by role ID", description = "Returns a specific role by its role ID")
    @GetMapping("/role/{roleId}")
    public ResponseEntity<RoleDTO> getRoleByRoleId(@PathVariable Integer roleId) {
        RoleDTO role = roleLookupService.getRoleByRoleId(roleId);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get all qualifications", description = "Retrieve a list of all qualifications")
    @GetMapping("/getAllQualificationsDetails")
    public ResponseEntity<List<QualificationLookupDTO>> getAllQualifications() {
        List<QualificationLookupDTO> qualifications = qualificationLookupService.getAllQualifications();
        return ResponseEntity.ok(qualifications);
    }
    @Operation(summary = "Get qualification by qualification ID", description = "Retrieve qualification details by qualification ID")
    @GetMapping("/qualification-id/{qualificationId}")
    public ResponseEntity<QualificationLookupDTO> getQualificationByQualificationId(@PathVariable Integer qualificationId) {
        QualificationLookupDTO qualification = qualificationLookupService.getQualificationByQualificationId(qualificationId);
        if (qualification != null) {
            return ResponseEntity.ok(qualification);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
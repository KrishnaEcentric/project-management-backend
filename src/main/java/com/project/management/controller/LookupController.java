package com.project.management.controller;

import com.project.management.dto.DzongkhagDTO;
import com.project.management.dto.GewogDTO;
import com.project.management.dto.VillageDTO;
import com.project.management.service.DzongkhagLookupService;
import com.project.management.service.GewogLookupService;
import com.project.management.service.VillageLookupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lookup")
@Tag(name = "Lookup API", description = "APIs for geographical lookup data")
public class LookupController {

    @Autowired
    private DzongkhagLookupService dzongkhagLookupService;

    @Autowired
    private GewogLookupService gewogLookupService;

    @Autowired
    private VillageLookupService villageLookupService;

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
}
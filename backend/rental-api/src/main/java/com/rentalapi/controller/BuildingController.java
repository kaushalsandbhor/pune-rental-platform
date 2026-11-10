
package com.rentalapi.controller;

import com.rentalapi.dto.request.BuildingRequest;
import com.rentalapi.dto.response.BuildingResponse;
import com.rentalapi.service.BuildingService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "https://pune-rental-platform.onrender.com")
@RestController
@RequestMapping("/api/buildings")
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping
    public BuildingResponse createBuilding(
            @Valid @RequestBody BuildingRequest request) {

        return buildingService.createBuilding(request);
    }

    @GetMapping
    public List<BuildingResponse> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @GetMapping("/{id}")
    public BuildingResponse getBuildingById(@PathVariable Long id) {
        return buildingService.getBuildingById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BuildingResponse> updateBuilding(
            @PathVariable Long id,
            @Valid @RequestBody BuildingRequest request) {

        BuildingResponse response =
                buildingService.updateBuilding(id, request);

        return ResponseEntity.ok(response);
    }

}

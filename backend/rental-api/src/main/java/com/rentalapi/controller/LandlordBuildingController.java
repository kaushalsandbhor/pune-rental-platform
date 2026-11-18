package com.rentalapi.controller;

import com.rentalapi.dto.request.BuildingRequest;
import com.rentalapi.dto.request.FlatRequest;
import com.rentalapi.dto.response.BuildingResponse;
import com.rentalapi.dto.response.FlatResponse;
import com.rentalapi.service.FlatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rentalapi.service.BuildingService;


import java.util.List;

@RestController
@RequestMapping("/api/landlord/buildings")
public class LandlordBuildingController {

    private final BuildingService buildingService;

    private final FlatService flatService;

    public LandlordBuildingController(BuildingService buildingService, FlatService flatService) {
        this.buildingService = buildingService;
        this.flatService = flatService;
    }

    @GetMapping
    public ResponseEntity<List<BuildingResponse>> getMyBuildings() {

        List<BuildingResponse> buildings =
                buildingService.getMyBuildings();

        return ResponseEntity.ok(buildings);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(
            @PathVariable Long id) {

        buildingService.deleteBuilding(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public BuildingResponse createBuilding(
            @Valid @RequestBody BuildingRequest request) {

        return buildingService.createBuilding(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingResponse> updateBuilding(
            @PathVariable Long id,
            @Valid @RequestBody BuildingRequest request) {

        BuildingResponse response =
                buildingService.updateBuilding(id, request);

        return ResponseEntity.ok(response);
    }

    @PostMapping ("/{buildingId}/flats")
    public FlatResponse createFlat(
            @PathVariable Long buildingId,
            @Valid @RequestBody FlatRequest request) {

        return flatService.createFlat(buildingId, request);
    }
}

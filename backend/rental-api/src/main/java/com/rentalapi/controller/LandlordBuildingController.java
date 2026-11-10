package com.rentalapi.controller;

import com.rentalapi.dto.response.BuildingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rentalapi.service.BuildingService;


import java.util.List;

@RestController
@RequestMapping("/api/landlord/buildings")
public class LandlordBuildingController {

    private final BuildingService buildingService;

    public LandlordBuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/landlord/buildings")
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



}

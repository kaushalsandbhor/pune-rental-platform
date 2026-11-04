package com.rentalapi.controller;

import com.rentalapi.dto.request.FlatRequest;
import com.rentalapi.dto.response.FlatResponse;
import com.rentalapi.entity.Flat;
import com.rentalapi.service.FlatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings/{buildingId}/flats")
public class FlatController {

    private final FlatService flatService;

    public FlatController(FlatService flatService) {
        this.flatService = flatService;
    }

    @PostMapping
    public FlatResponse createFlat(
            @PathVariable Long buildingId,
            @Valid @RequestBody FlatRequest request) {

        return flatService.createFlat(buildingId, request);
    }

    @GetMapping
    public List<FlatResponse> getFlatsByBuilding(
            @PathVariable Long buildingId) {

        return flatService.getFlatsByBuilding(buildingId);
    }

    @GetMapping("/{flatId}")
    public FlatResponse getFlatById(
            @PathVariable Long buildingId,
            @PathVariable Long flatId) {

        return flatService.getFlatById(buildingId, flatId);
    }
}
package com.rentalapi.service;

import com.rentalapi.dto.request.BuildingRequest;
import com.rentalapi.dto.response.BuildingResponse;
import com.rentalapi.entity.Building;
import com.rentalapi.exception.ResourceNotFoundException;
import com.rentalapi.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public BuildingResponse createBuilding(BuildingRequest request) {

        Building building = convertToEntity(request);

        Building savedBuilding = buildingRepository.save(building);

        return convertToResponse(savedBuilding);
    }

    public List<BuildingResponse> getAllBuildings() {

        return buildingRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public BuildingResponse getBuildingById(Long id) {

        Building building = buildingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Building not found with ID: " + id)
                );

        return convertToResponse(building);
    }

    private Building convertToEntity(BuildingRequest request) {

        Building building = new Building();

        building.setName(request.getName());
        building.setAddress(request.getAddress());
        building.setLocality(request.getLocality());
        building.setCity(request.getCity());
        building.setPincode(request.getPincode());
        building.setDescription(request.getDescription());

        return building;
    }
    private BuildingResponse convertToResponse(Building building) {

        BuildingResponse response = new BuildingResponse();

        response.setId(building.getId());
        response.setName(building.getName());
        response.setAddress(building.getAddress());
        response.setLocality(building.getLocality());
        response.setCity(building.getCity());
        response.setPincode(building.getPincode());
        response.setDescription(building.getDescription());
        response.setCreatedAt(building.getCreatedAt());

        return response;
    }
}
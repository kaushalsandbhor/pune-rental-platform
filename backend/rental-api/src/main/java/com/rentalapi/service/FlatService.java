package com.rentalapi.service;

import com.rentalapi.dto.request.FlatRequest;
import com.rentalapi.dto.response.FlatResponse;
import com.rentalapi.entity.Building;
import com.rentalapi.entity.Flat;
import com.rentalapi.exception.ResourceNotFoundException;
import com.rentalapi.repository.BuildingRepository;
import com.rentalapi.repository.FlatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FlatService {

    private final FlatRepository flatRepository;
    private final BuildingRepository buildingRepository;

    public FlatService(
            FlatRepository flatRepository,
            BuildingRepository buildingRepository) {

        this.flatRepository = flatRepository;
        this.buildingRepository = buildingRepository;
    }

    private FlatResponse convertToResponse(Flat flat) {

        FlatResponse response = new FlatResponse();

        response.setId(flat.getId());
        response.setFlatNumber(flat.getFlatNumber());
        response.setFloorNumber(flat.getFloorNumber());
        response.setFlatType(flat.getFlatType());
        response.setMonthlyRent(flat.getMonthlyRent());
        response.setDeposit(flat.getDeposit());
        response.setParking(flat.isParking());
        response.setStatus(flat.getStatus());
        response.setBuildingId(flat.getBuilding().getId());

        return response;
    }

    public FlatResponse createFlat(Long buildingId, FlatRequest request) {

        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Building not found with ID: " + buildingId)
                );

        Flat flat = new Flat();

        flat.setFlatNumber(request.getFlatNumber());
        flat.setFloorNumber(request.getFloorNumber());
        flat.setFlatType(request.getFlatType());
        flat.setMonthlyRent(request.getMonthlyRent());
        flat.setDeposit(request.getDeposit());
        flat.setParking(request.isParking());
        flat.setStatus(request.getStatus());

        flat.setBuilding(building);

        Flat savedFlat = flatRepository.save(flat);

        return convertToResponse(savedFlat);
    }

    @Transactional(readOnly = true)
    public List<FlatResponse> getFlatsByBuilding(Long buildingId) {

        if (!buildingRepository.existsById(buildingId)) {
            throw new RuntimeException("Building not found");
        }

        return flatRepository.findByBuildingId(buildingId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public FlatResponse getFlatById(Long buildingId, Long flatId) {

        Flat flat = flatRepository
                .findByIdAndBuildingId(flatId, buildingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Flat not found with ID: " + flatId)
                );

        return convertToResponse(flat);
    }
}
package com.rentalapi.repository;

import com.rentalapi.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    List<Building> findByOwnerId(Long ownerId);

    Optional<Building> findByIdAndOwnerId(Long id, Long ownerId);

}
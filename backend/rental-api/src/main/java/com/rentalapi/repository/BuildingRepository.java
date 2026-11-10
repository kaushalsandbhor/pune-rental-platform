package com.rentalapi.repository;

import com.rentalapi.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    List<Building> findByOwnerId(Long ownerId);

}
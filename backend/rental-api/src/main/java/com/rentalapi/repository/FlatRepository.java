package com.rentalapi.repository;

import com.rentalapi.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FlatRepository extends JpaRepository<Flat, Long> {

    List<Flat> findByBuildingId(Long buildingId);

    Optional<Flat> findByIdAndBuildingId(Long id, Long buildingId);

}
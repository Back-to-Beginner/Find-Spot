package com.backend.domain.location.domain.repository;

import com.backend.domain.location.domain.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaLocationRepository extends JpaRepository<Location, Long> {

    Location findLocationByName(String name);
}

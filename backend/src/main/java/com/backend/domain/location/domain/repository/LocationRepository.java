package com.backend.domain.location.domain.repository;

import com.backend.domain.location.domain.entity.Location;
import com.backend.global.domain.code.domain.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findLocationByName(String name);

    boolean existsByNameAndRegionCode(String name, Code regionCode);
}

package com.backend.repository;

import com.backend.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    Location save(Location location);

    Optional<Location> findById(Long id);

    List<Location> findByTripId(Long trip_id);

    List<Location> findAll();
}

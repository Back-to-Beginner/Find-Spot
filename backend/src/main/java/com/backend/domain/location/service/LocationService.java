package com.backend.domain.location.service;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.location.domain.repository.JpaLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class LocationService {
    private final JpaLocationRepository locationRepository;

    public Long save(Location location) {
        locationRepository.save(location);
        return location.getId();
    }

    public List<Location> findAllLocation() {
        return locationRepository.findAll();
    }

    public Location findLocationById(Long locationId) {
        return locationRepository.getById(locationId);
    }
    public List<Location> findByTripId(Long tripId) {
        return locationRepository.findByTripId(tripId);
    }
}

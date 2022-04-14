package com.backend.service;

import com.backend.entity.Location;
import com.backend.repository.JpaLocationRepository;
import com.backend.repository.LocationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(JpaLocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Long save(Location location) {
        locationRepository.save(location);
        return location.getId();
    }

    public List<Location> findAllLocation() {
        return locationRepository.findAll();
    }

    public List<Location> findByTripId(Long tripId) {
        return locationRepository.findByTripId(tripId);
    }
}

package com.backend.domain.location.service;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.location.domain.repository.JpaLocationRepository;
import com.backend.domain.location.exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class LocationService {
    private final JpaLocationRepository locationRepository;

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> findAllLocation() {
        return locationRepository.findAll();
    }

    public Location findLocationById(Long id) {
        return locationRepository.findById(id).orElseThrow(LocationNotFoundException::new);
    }

    public List<Location> findLocationByTripId(Long trip_id) {
        return locationRepository.findAllByTripId(trip_id);
    }

    public void deleteLocationById(Long id) {
        findLocationById(id).deleteLocation();
    }

}

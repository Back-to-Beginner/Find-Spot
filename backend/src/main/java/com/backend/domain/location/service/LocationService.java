package com.backend.domain.location.service;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.location.domain.repository.JpaLocationRepository;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class LocationService {
    private final JpaLocationRepository locationRepository;

    public Location create(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location findOneById(Long id) {
        return locationRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "장소를 찾을 수 없습니다.")
        );
    }
    public void deleteOneById(Long id) {
        findOneById(id).deleteLocation();
    }

}

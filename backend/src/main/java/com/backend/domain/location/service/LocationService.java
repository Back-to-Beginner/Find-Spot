package com.backend.domain.location.service;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.location.domain.repository.LocationRepository;
import com.backend.domain.location.dto.LocationMapper;
import com.backend.domain.location.dto.LocationResponseDto;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.backend.global.error.ErrorCode.*;

@RequiredArgsConstructor
@Transactional
@Service
public class LocationService {
    private final LocationRepository repository;
    private final LocationMapper mapper;

    public Location create(Location location) {
        return Stream.of(location)
                .filter(l -> !repository.existsByNameAndRegionCode(l.getName(), l.getRegionCode()))
                .map(repository::save)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "장소 저장에 실패하였습니다."));
    }

    public List<Location> findAll() {
        return repository.findAll();
    }

    public Location findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(NOT_FOUND, "장소를 찾을 수 없습니다.")
        );
    }

    public LocationResponseDto findByIdAsDto(Long id) {
        return Stream.of(id)
                .map(this::findById)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "장소를 찾을 수 없습니다."));
    }

    public void deleteOneById(Long id) {
        repository.delete(findById(id));
    }

    public Location findByName(String name) {
        return Stream.of(name)
                .map(repository::findLocationByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "이름으로 장소를 찾을 수 없습니다."));
    }
}

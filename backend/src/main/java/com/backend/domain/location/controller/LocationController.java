package com.backend.domain.location.controller;

import com.backend.domain.location.dto.LocationRequest;
import com.backend.domain.location.dto.LocationResponse;
import com.backend.domain.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
@RestController
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationResponse> createLocation(@Validated @RequestBody LocationRequest locationRequest) {
        return ResponseEntity.ok(LocationResponse.of(locationService.createLocation(locationRequest.toEntity())));
    }

    @GetMapping
    public ResponseEntity<List<LocationResponse>> findAllLocation() {
        return ResponseEntity.ok(locationService
                .findAllLocation()
                .stream()
                .map(LocationResponse::of)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> findLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(LocationResponse
                .of(locationService
                        .findLocationById(id)
                )
        );
    }

    @GetMapping("/trips/{trip_id}")
    public ResponseEntity<List<LocationResponse>> findAllLocationByTripId(@PathVariable Long trip_id) {
        return ResponseEntity.ok(locationService
                .findLocationByTripId(trip_id)
                .stream()
                .map(LocationResponse::of)
                .collect(Collectors.toList())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLocationById(@PathVariable Long id) {
        locationService.deleteLocationById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}

package com.backend.domain.location.controller;

import com.backend.domain.location.dto.LocationRequest;
import com.backend.domain.location.dto.LocationResponse;
import com.backend.domain.location.service.LocationService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
@RestController
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse createLocation(@Validated @RequestBody LocationRequest locationRequest) {
        return ApiResponse.created(
                LocationResponse.of(
                        locationService.createLocation(
                                locationRequest.toEntity()
                        )
                )
        );
    }

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAllLocation() {
        return ApiResponse.success(
                locationService.findAllLocation()
                        .stream()
                        .map(LocationResponse::of)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findLocationById(@PathVariable Long id) {
        return ApiResponse.success(
                LocationResponse.of(
                        locationService.findLocationById(id)
                )
        );
    }

//    @GetMapping("/trips/{trip_id}")
//    @ResponseStatus(OK)
//    public ApiResponse findAllLocationByTripId(@PathVariable Long trip_id) {
//        return ApiResponse.success(locationService
//                .findLocationByTripId(trip_id)
//                .stream()
//                .map(LocationResponse::of)
//                .collect(Collectors.toList())
//        );
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse deleteLocationById(@PathVariable Long id) {
        locationService.deleteLocationById(id);
        return ApiResponse.noContent();
    }
}

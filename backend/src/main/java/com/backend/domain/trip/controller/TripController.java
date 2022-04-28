package com.backend.domain.trip.controller;

import com.backend.domain.image.service.ImageService;
import com.backend.domain.location.service.LocationService;
import com.backend.domain.trip.dto.TripRequest;
import com.backend.domain.trip.dto.TripResponse;
import com.backend.domain.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/v1/trips")
@RestController
public class TripController {
    private final TripService tripService;
    private final LocationService locationService;
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@Validated @RequestBody TripRequest tripRequest) {

        return ResponseEntity.ok(TripResponse.of(tripRequest.toEntity()));
    }

    @GetMapping
    public ResponseEntity<List<TripResponse>> findAllTrip() {
        return ResponseEntity.ok(tripService
                .findAllTrip()
                .stream()
                .map(TripResponse::of)
                .collect(Collectors.toList())
        );
    }

    //TODO 나머지 api 만들어야 함
}

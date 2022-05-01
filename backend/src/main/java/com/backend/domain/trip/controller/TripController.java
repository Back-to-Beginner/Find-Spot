package com.backend.domain.trip.controller;

import com.backend.domain.trip.dto.TripRequest;
import com.backend.domain.trip.dto.TripResponse;
import com.backend.domain.trip.service.TripService;
import com.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final UserService userService;

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@Validated @RequestBody TripRequest tripRequest) {
        return ResponseEntity.ok(TripResponse
                .of(tripService
                        .save(tripRequest
                                .toEntity(userService)
                        )
                )
        );
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

    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> findOneTripById(@PathVariable Long id) {
        return ResponseEntity.ok(TripResponse.of(tripService.findOneById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTripById(@PathVariable Long id) {
        tripService.deleteOneById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripResponse> updateTripById(@PathVariable Long id, @Validated @RequestBody TripRequest tripRequest) {
        return ResponseEntity.ok(TripResponse.of(tripService.updateOneById(id, tripRequest)));
    }
}

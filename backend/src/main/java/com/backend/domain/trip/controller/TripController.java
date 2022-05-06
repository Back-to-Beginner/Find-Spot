package com.backend.domain.trip.controller;

import com.backend.domain.trip.dto.TripRequest;
import com.backend.domain.trip.dto.TripResponse;
import com.backend.domain.trip.service.TripService;
import com.backend.domain.user.service.UserService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/trips")
@RestController
public class TripController {
    private final TripService tripService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse createTrip(@Validated @RequestBody TripRequest tripRequest) {
        return created(
                TripResponse.of(
                        tripService.save(
                                tripRequest.toEntity(userService)
                        )
                )
        );
    }

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAllTrip() {
        return ok(tripService
                .findAllTrip()
                .stream()
                .map(TripResponse::of)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findOneTripById(@PathVariable Long id) {
        return ok(
                TripResponse.of(
                        tripService.findOneById(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse deleteTripById(@PathVariable Long id) {
        tripService.deleteOneById(id);
        return noContent();
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public ApiResponse updateTripById(@PathVariable Long id, @Validated @RequestBody TripRequest tripRequest) {
        return created(TripResponse.of(tripService.updateOneById(id, tripRequest)));
    }
}

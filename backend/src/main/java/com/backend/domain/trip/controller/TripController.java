package com.backend.domain.trip.controller;

import com.backend.domain.trip.dto.TripRequestDto;
import com.backend.domain.trip.service.TripService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/trips")
@RestController
public class TripController {
    private final TripService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse createTrip(@Validated @RequestBody TripRequestDto tripRequestDto) {
        return created(service.save(tripRequestDto));
    }

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAllTrip() {
        return ok(service.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findOneTripById(@PathVariable Long id) {
        return ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse deleteTripById(@PathVariable Long id) {
        service.deleteById(id);
        return noContent();
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public ApiResponse updateTripById(@PathVariable Long id, @Validated @RequestBody TripRequestDto tripRequestDto) {
        return created(service.updateById(id, tripRequestDto));
    }

    @GetMapping("/tag")
    @ResponseStatus(OK)
    public ApiResponse findByTagId(@RequestParam String name) {
        return ok(service.findByTagName(name));
    }
}

package com.backend.domain.moment.controller;

import com.backend.domain.moment.dto.MomentMapper;
import com.backend.domain.moment.dto.MomentRequest;
import com.backend.domain.moment.service.MomentService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/moments")
@RequiredArgsConstructor
public class MomentController {

    private final MomentService service;
    private final MomentMapper mapper;

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAll() {
        return ok(service.findAll());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse create(@Validated @RequestBody MomentRequest request) {
        return Stream.of(request)
                .map(mapper::toEntity)
                .map(service::create)
                .map(ApiResponse::created)
                .findAny()
                .orElseThrow();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findOne(@PathVariable Long id) {
        return Stream.of(id)
                .map(service::findByIdAsDto)
                .map(ApiResponse::ok)
                .findAny()
                .orElseThrow();
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public ApiResponse update(@PathVariable Long id, @Validated @RequestBody MomentRequest request) {
        return Stream.of(request)
                .map(mapper::toEntity)
                .map(entity -> service.updateById(id, entity))
                .map(ApiResponse::created)
                .findAny()
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse delete(@PathVariable Long id) {
        service.deleteById(id);
        return noContent();
    }

    @GetMapping("/trips/{tripId}")
    @ResponseStatus(OK)
    public ApiResponse findAllByTrip(@PathVariable Long tripId) {
        return ok(service.findAllByTripId(tripId));
    }
}

package com.backend.domain.location.controller;

import com.backend.domain.location.dto.LocationMapper;
import com.backend.domain.location.dto.LocationRequest;
import com.backend.domain.location.dto.LocationResponse;
import com.backend.domain.location.service.LocationService;
import com.backend.global.dto.ApiResponse;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.MappingFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/locations")
@RestController
public class LocationController {

    private final LocationService service;

    private final LocationMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse createLocation(@Validated @RequestBody LocationRequest locationRequest) {
        return Stream.of(locationRequest)
                .map(mapper::toEntity)
                .map(service::create)
                .map(mapper::fromEntity)
                .map(ApiResponse::created)
                .findAny()
                .orElseThrow(() -> new MappingFailException(ErrorCode.INVALID_JSON_FORMAT, "매핑에 실패하였습니다."));
    }

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAllLocation() {
        return ok(service.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findLocationById(@PathVariable Long id) {
        return Stream.of(id)
                .map(service::findOneById)
                .map(mapper::fromEntity)
                .map(ApiResponse::ok)
                .findAny()
                .orElseThrow(() -> new MappingFailException(ErrorCode.INVALID_JSON_FORMAT, "데이터 매핑에 실패하였습니다."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse deleteLocationById(@PathVariable Long id) {
        service.deleteOneById(id);
        return noContent();
    }

    @GetMapping("/name")
    @ResponseStatus(OK)
    public ApiResponse findByName(@RequestParam String name){
        return Stream.of(name)
                .map(service::findByName)
                .map(mapper::fromEntity)
                .map(ApiResponse::ok)
                .findAny()
                .orElseThrow(() -> new MappingFailException(ErrorCode.EXCEPTION, "데이터 매핑에 실패하였습니다."));
    }
}

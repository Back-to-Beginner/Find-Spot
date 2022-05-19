package com.backend.global.domain.code.controller;

import com.backend.domain.trip.dto.TripRequestDto;
import com.backend.global.domain.code.dto.CodeRequestDto;
import com.backend.global.domain.code.service.CodeService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/codes")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse create(@Validated @RequestBody CodeRequestDto request) {
        return created(service.save(request));
    }

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAll() {
        return ok(service.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findById(@PathVariable Long id) {
        return ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse delete(@PathVariable Long id) {
        service.deleteById(id);
        return noContent();
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public ApiResponse update(@PathVariable Long id, @Validated @RequestBody CodeRequestDto request) {
        return created(service.updateById(id, request));
    }
}

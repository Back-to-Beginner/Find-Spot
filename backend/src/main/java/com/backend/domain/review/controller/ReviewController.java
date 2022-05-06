package com.backend.domain.review.controller;

import com.backend.domain.review.dto.ReviewMapper;
import com.backend.domain.review.dto.ReviewRequest;
import com.backend.domain.review.dto.ReviewResponse;
import com.backend.domain.review.service.ReviewService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    //TODO findAll
    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAll() {
        return ok(
                reviewService.findAll()
                        .stream()
                        .map(ReviewResponse::of)
                        .collect(Collectors.toList())
        );
    }

    //TODO create
    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse create(@Validated @RequestBody ReviewRequest request) {
        return created(
                reviewService.create(
                        mapper.toEntity(request)
                )
        );
    }

    //TODO findOne
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findOne(@PathVariable Long id) {
        return ok(
                reviewService.findOneById(id)
        );
    }

    //TODO update
    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public ApiResponse update(@PathVariable Long id, @Validated @RequestBody ReviewRequest request) {
        return created(
                reviewService.updateOneById(
                        id, mapper.toEntity(request)
                )
        );
    }

    //TODO delete
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse delete(@PathVariable Long id) {
        reviewService.deleteOneById(id);
        return noContent();
    }

    //TODO findAllByTrip
    @GetMapping("/trips/{tripId}")
    @ResponseStatus(OK)
    public ApiResponse findAllByTrip(@PathVariable Long tripId) {
        return ok(reviewService.findAllByTripId(tripId));
    }
}

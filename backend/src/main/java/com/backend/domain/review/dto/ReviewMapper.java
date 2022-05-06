package com.backend.domain.review.dto;

import com.backend.domain.location.service.LocationService;
import com.backend.domain.review.domain.entity.Review;
import com.backend.domain.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMapper {

    private final LocationService locationService;
    private final TripService tripService;

    public Review toEntity(ReviewRequest request) {
        return Review.builder()
                .location(locationService.findOneById(request.getLocation_id()))
                .trip(tripService.findOneById(request.getTrip_id()))
                .content(request.getContent())
                .cost(request.getCost())
                .build();
    }
}

package com.backend.domain.trip.dto;

import com.backend.domain.trip.domain.entity.Trip;
import lombok.Builder;

@Builder
public class TripResponse {

    public static TripResponse of(Trip trip) {
        return TripResponse.builder().build();
    }
}

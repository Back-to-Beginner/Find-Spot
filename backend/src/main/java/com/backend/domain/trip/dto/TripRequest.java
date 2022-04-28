package com.backend.domain.trip.dto;

import com.backend.domain.trip.domain.entity.Trip;

public class TripRequest {

    public Trip toEntity() {
        return Trip.builder().build();
    }
}

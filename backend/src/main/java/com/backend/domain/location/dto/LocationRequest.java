package com.backend.domain.location.dto;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.trip.service.TripService;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@RequiredArgsConstructor
public class LocationRequest {

    private final TripService tripService;

    @NotEmpty
    private Long trip_id;

    @NotEmpty
    private String region;

    @NotEmpty
    private String name;

    private int cost = 0;

    @NotEmpty
    private double latitude;

    @NotEmpty
    private double longitude;

    public Location toEntity() {
        return Location.builder()
                .trip(tripService.findOneById(this.trip_id))
                .region(this.region)
                .name(this.name)
                .cost(this.cost)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}

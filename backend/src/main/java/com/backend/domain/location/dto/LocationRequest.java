package com.backend.domain.location.dto;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.trip.service.TripService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
public class LocationRequest {
    @NotNull
    private Long trip_id;

    @NotNull
    private String region;

    @NotNull
    private String name;

    @NotNull
    private String review;

    @NotNull
    private int cost;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    public Location toEntity(TripService tripService) {
        return Location.builder()
                .trip(tripService.findOneById(this.trip_id))
                .region(this.region)
                .name(this.name)
                .review(this.review)
                .cost(this.cost)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}

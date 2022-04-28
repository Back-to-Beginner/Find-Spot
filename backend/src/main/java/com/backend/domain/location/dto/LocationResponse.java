package com.backend.domain.location.dto;

import com.backend.domain.location.domain.entity.Location;
import lombok.Builder;

@Builder
public class LocationResponse {

    private Long id;

    private Long trip_id;

    private String region;

    private String name;

    private String review;

    private int cost;

    private double latitude;

    private double longitude;

    public static LocationResponse of(Location location) {
        return LocationResponse.builder()
                .id(location.getId())
                .trip_id(location.getTrip().getId())
                .region(location.getRegion())
                .name(location.getName())
                .review(location.getReview())
                .cost(location.getCost())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}

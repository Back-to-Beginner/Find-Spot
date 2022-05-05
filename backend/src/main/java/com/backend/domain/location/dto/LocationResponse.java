package com.backend.domain.location.dto;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.trip.domain.entity.Trip;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationResponse {

    private Long id;

    private Trip trip;

    private String region;

    private String name;

    private String review;

    private int cost;

    private double latitude;

    private double longitude;

    public static LocationResponse of(Location location) {
        return LocationResponse.builder()
                .id(location.getId())
                .region(location.getRegion())
                .name(location.getName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}

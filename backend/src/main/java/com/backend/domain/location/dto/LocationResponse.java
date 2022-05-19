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

    private String region;

    private String name;

    private double latitude;

    private double longitude;

}

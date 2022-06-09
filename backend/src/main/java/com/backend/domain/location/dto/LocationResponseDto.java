package com.backend.domain.location.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationResponseDto {

    private Long id;

    private String region;

    private String name;

    private double latitude;

    private double longitude;

}

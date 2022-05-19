package com.backend.domain.location.dto;

import com.backend.domain.location.domain.entity.Location;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
public class LocationRequest {

    @NotNull
    private String region;

    @NotNull
    private String name;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

}

package com.backend.domain.moment.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MomentRequest {

    @NotNull
    private Long location_id;

    @NotNull
    private Long trip_id;

    @NotNull
    private String content;

    @NotNull
    private int cost;

}

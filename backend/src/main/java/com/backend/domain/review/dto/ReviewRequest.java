package com.backend.domain.review.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReviewRequest {

    @NotNull
    private Long location_id;

    @NotNull
    private Long trip_id;

    @NotNull
    private String content;

    @NotNull
    private int cost;

}

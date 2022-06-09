package com.backend.domain.trip.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Builder
public class TripResponseDto {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    private String title;

    @JsonProperty("begin_date")
    private LocalDate beginDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("full_cost")
    private int fullCost;

    private Set<String> tags;
}

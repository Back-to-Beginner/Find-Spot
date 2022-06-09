package com.backend.domain.moment.dto;

import com.backend.domain.location.dto.LocationResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MomentResponseDto {

    private Long id;

    @JsonProperty("trip_id")
    private Long tripId;

    private LocationResponseDto location;

    private String content;

    private int cost;

    private int sequence;
}

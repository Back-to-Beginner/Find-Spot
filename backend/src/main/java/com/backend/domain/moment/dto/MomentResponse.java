package com.backend.domain.moment.dto;

import com.backend.domain.moment.domain.entity.Moment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MomentResponse {

    private Long id;

    private Long location_id;

    private Long trip_id;

    private String content;

    private int cost;
}

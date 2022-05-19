package com.backend.domain.trip.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
public class TripResponse {

    private Long id;

    private Long user_id;

    private String title;

    private java.sql.Date begin_date;

    private java.sql.Date end_date;

    private int fullCost;

    private Set<String> tags;
}

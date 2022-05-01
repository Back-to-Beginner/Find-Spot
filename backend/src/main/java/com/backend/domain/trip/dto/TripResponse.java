package com.backend.domain.trip.dto;

import com.backend.domain.trip.domain.entity.Trip;
import com.backend.domain.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TripResponse {

    private Long id;

    private User user;

    private String title;

    private String review;

    private java.sql.Date begin_date;

    private java.sql.Date end_date;

    private int fullCost;

    public static TripResponse of(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .user(trip.getUser())
                .title(trip.getTitle())
                .review(trip.getReview())
                .begin_date(trip.getBegin_date())
                .end_date(trip.getEnd_date())
                .fullCost(trip.getFullCost())
                .build();
    }
}

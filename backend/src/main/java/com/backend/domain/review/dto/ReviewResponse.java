package com.backend.domain.review.dto;

import com.backend.domain.review.domain.entity.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {

    private Long id;

    private Long location_id;

    private Long trip_id;

    private String content;

    private int cost;

    public static ReviewResponse of(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .location_id(review.getLocation().getId())
                .trip_id(review.getTrip().getId())
                .content(review.getContent())
                .cost(review.getCost())
                .build();
    }
}

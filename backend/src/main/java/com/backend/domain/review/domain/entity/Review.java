package com.backend.domain.review.domain.entity;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.trip.domain.entity.Trip;
import com.backend.global.domain.basetime.domain.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    private String content;

    private int cost = 0;

    public Review update(Review review){
        this.location = review.location;
        this.trip = review.trip;
        this.content = review.content;
        this.cost = review.cost;
        return this;
    }

}
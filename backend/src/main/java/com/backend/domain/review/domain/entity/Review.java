package com.backend.domain.review.domain.entity;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.trip.domain.entity.Trip;
import com.backend.global.domain.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
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
}
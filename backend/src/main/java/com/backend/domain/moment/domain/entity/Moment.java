package com.backend.domain.moment.domain.entity;

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
public class Moment extends BaseTimeEntity {
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

    public Moment update(Moment moment){
        this.location = moment.location;
        this.trip = moment.trip;
        this.content = moment.content;
        this.cost = moment.cost;
        return this;
    }

}
package com.backend.domain.tag.domain.entity;

import com.backend.domain.trip.domain.entity.Trip;

import javax.persistence.*;

@Entity
public class TagTripMapper {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

}

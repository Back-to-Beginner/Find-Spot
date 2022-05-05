package com.backend.domain.trip.domain.entity;

import javax.persistence.*;

import com.backend.domain.tag.domain.entity.Tag;
import com.backend.domain.trip.dto.TripRequest;
import com.backend.domain.user.domain.entity.User;
import com.backend.global.domain.basetime.domain.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Trip extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private java.sql.Date begin_date;

    private java.sql.Date end_date;

    private int fullCost;

    @ManyToMany
    @JoinColumn(name = "tag_id")
    private Set<Tag> tagSet;

    @Builder
    public Trip(User user, String title, Date begin_date, Date end_date, int fullCost) {
        this.user = user;
        this.title = title;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.fullCost = fullCost;
    }

    public Trip updateTrip(TripRequest tripRequest) {
        this.title = tripRequest.getTitle();
        this.begin_date = tripRequest.getBeginDate();
        this.end_date = tripRequest.getEndDate();
        this.fullCost = tripRequest.getFullCost();
        return this;
    }

}

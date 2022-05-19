package com.backend.domain.trip.domain.entity;

import javax.persistence.*;

import com.backend.domain.tag.domain.entity.Tag;
import com.backend.domain.tag.dto.TagMapper;
import com.backend.domain.trip.dto.TripMapper;
import com.backend.domain.trip.dto.TripRequestDto;
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

    @Column(nullable = false)
    private String title;

    private java.sql.Date beginDate;

    private java.sql.Date endDate;

    private int fullCost;

    @ManyToMany
    @JoinColumn(name = "tag_id")
    private Set<Tag> tagSet;

    @Builder
    public Trip(User user, String title, Date beginDate, Date endDate, int fullCost, Set<Tag> tagSet) {
        this.user = user;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.fullCost = fullCost;
        this.tagSet = tagSet;
    }

    public Trip update(Trip trip) {
        this.title = trip.getTitle();
        this.beginDate = trip.getBeginDate();
        this.endDate = trip.getEndDate();
        this.fullCost = trip.getFullCost();
        this.tagSet = trip.tagSet;
        return this;
    }

}

package com.backend.domain.comment.domain.entity;

import com.backend.domain.trip.domain.entity.Trip;
import com.backend.domain.user.domain.entity.User;
import com.backend.global.domain.basetime.domain.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

}
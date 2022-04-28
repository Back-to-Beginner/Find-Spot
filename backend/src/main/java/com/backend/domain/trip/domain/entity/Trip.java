package com.backend.domain.trip.domain.entity;

import javax.persistence.*;

import com.backend.domain.tag.domain.entity.Tag;
import com.backend.domain.user.domain.entity.User;
import com.backend.global.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Builder
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

    private String review;

    private java.sql.Date begin_date;

    private java.sql.Date end_date;

    private int fullCost;

}

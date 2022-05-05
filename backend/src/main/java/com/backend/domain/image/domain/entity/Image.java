package com.backend.domain.image.domain.entity;

import javax.persistence.*;

import com.backend.domain.review.domain.entity.Review;
import com.backend.domain.trip.domain.entity.Trip;
import com.backend.domain.user.domain.entity.User;
import com.backend.global.domain.entity.BaseTimeEntity;
import com.backend.domain.location.domain.entity.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Image extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 300, nullable = false)
    private String path;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    public void deleteImage() {
        this.isDeleted = true;
    }
    @Builder
    public Image(Review review, String path) {
        this.review = review;
        this.path = path;
    }

    @Builder
    public Image(User user, String path) {
        this.user = user;
        this.path = path;
    }
}

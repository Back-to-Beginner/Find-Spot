package com.backend.domain.review.domain.repository;

import com.backend.domain.review.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByTripId(Long tripId);
}

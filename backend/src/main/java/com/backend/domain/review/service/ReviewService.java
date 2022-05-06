package com.backend.domain.review.service;

import com.backend.domain.review.domain.entity.Review;
import com.backend.domain.review.domain.repository.JpaReviewRepository;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final JpaReviewRepository reviewRepository;

    //TODO findAll
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    //TODO create
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    //TODO findOne
    public Review findOneById(Long id){
        return reviewRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "리뷰를 찾을 수 없습니다.")
        );
    }

    //TODO update
    public Review updateOneById(Long id, Review review) {
        return reviewRepository.save(findOneById(id).update(review));
    }

    //TODO delete
    public void deleteOneById(Long id) {
        reviewRepository.delete(findOneById(id));
    }

    //TODO findAllByTrip
    public List<Review> findAllByTripId(Long tripId) {
        List<Review> reviewList = reviewRepository.findAllByTripId(tripId);
        if (reviewList.isEmpty()) throw new NotFoundException(ErrorCode.NOT_FOUND, "등록된 리뷰가 없습니다.");
        return reviewList;
    }
}

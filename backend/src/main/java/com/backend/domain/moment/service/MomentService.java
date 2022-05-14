package com.backend.domain.moment.service;

import com.backend.domain.moment.domain.entity.Moment;
import com.backend.domain.moment.domain.repository.JpaMomentRepository;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MomentService {

    private final JpaMomentRepository reviewRepository;

    public List<Moment> findAll() {
        return reviewRepository.findAll();
    }

    public Moment create(Moment moment) {
        return reviewRepository.save(moment);
    }

    public Moment findOneById(Long id) {
        return reviewRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "리뷰를 찾을 수 없습니다.")
        );
    }

    public Moment updateOneById(Long id, Moment moment) {
        return reviewRepository.save(findOneById(id).update(moment));
    }

    public void deleteOneById(Long id) {
        reviewRepository.delete(findOneById(id));
    }

    public List<Moment> findAllByTripId(Long tripId) {
        List<Moment> momentList = reviewRepository.findAllByTripId(tripId);
        if (momentList.isEmpty()) throw new NotFoundException(ErrorCode.NOT_FOUND, "등록된 리뷰가 없습니다.");
        return momentList;
    }
}

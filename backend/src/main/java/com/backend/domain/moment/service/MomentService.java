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

    private final JpaMomentRepository repository;

    public List<Moment> findAll() {
        return repository.findAll();
    }

    public Moment create(Moment moment) {
        return repository.save(moment);
    }

    public Moment findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "리뷰를 찾을 수 없습니다.")
        );
    }

    public Moment updateById(Long id, Moment moment) {
        return repository.save(findById(id).update(moment));
    }

    public void deleteById(Long id) {
        repository.delete(findById(id));
    }

    public List<Moment> findAllByTripId(Long id) {
        List<Moment> momentList = repository.findAllByTripId(id);
        if (momentList.isEmpty()) throw new NotFoundException(ErrorCode.NOT_FOUND, "등록된 리뷰가 없습니다.");
        return momentList;
    }
}

package com.backend.domain.moment.domain.repository;

import com.backend.domain.moment.domain.entity.Moment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMomentRepository extends JpaRepository<Moment, Long> {
    List<Moment> findAllByTripId(Long tripId);
}

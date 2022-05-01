package com.backend.domain.trip.domain.repository;

import com.backend.domain.trip.domain.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTripRepository extends JpaRepository<Trip, Long> {
}

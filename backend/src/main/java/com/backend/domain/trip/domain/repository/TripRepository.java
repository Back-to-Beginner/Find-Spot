package com.backend.domain.trip.domain.repository;

import com.backend.domain.tag.domain.entity.Tag;
import com.backend.domain.trip.domain.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByTagSetContaining(Tag tag);
}

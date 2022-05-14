package com.backend.domain.trip.domain.repository;

import com.backend.domain.tag.domain.entity.Tag;
import com.backend.domain.trip.domain.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaTripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByTagSetContaining(Tag tag);
}

package com.backend.repository;

import com.backend.entity.Trip;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TripRepository {
    Trip save(Trip trip);

    Optional<Trip> findById(Long id);

    List<Trip> findByTitle(String title);

    List<Trip> findByDate(Date date);

    List<Trip> findByFullCost(Integer cost);

    List<Trip> findAll();
}

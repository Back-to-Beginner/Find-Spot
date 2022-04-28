package com.backend.domain.trip.service;

import com.backend.domain.trip.domain.entity.Trip;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TripService {
    private final TripRepository tripRepository;

    public TripService(JpaTripRpository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Long save(Trip trip) {
        tripRepository.save(trip);
        return trip.getId();
    }

    public List<Trip> findAllTrip() {
        return tripRepository.findAll();
    }
}

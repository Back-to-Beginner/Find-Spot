package com.backend.domain.trip.service;

import com.backend.domain.trip.domain.entity.Trip;
import com.backend.domain.trip.domain.repository.JpaTripRepository;
import com.backend.domain.trip.dto.TripRequest;
import com.backend.domain.trip.exception.TripNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class TripService {
    private final JpaTripRepository tripRepository;

    public Trip save(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip findOneById(Long id) {
        return tripRepository.findById(id).orElseThrow(TripNotFoundException::new);
    }

    public List<Trip> findAllTrip() {
        return tripRepository.findAll();
    }

    public void deleteOneById(Long id) {
        tripRepository.delete(findOneById(id));
    }

    public Trip updateOneById(Long id, TripRequest tripRequest) {
        return tripRepository.save(findOneById(id).updateTrip(tripRequest));
    }
}

package com.backend.domain.trip.service;

import com.backend.domain.trip.domain.entity.Trip;
import com.backend.domain.trip.domain.repository.JpaTripRepository;
import com.backend.domain.trip.dto.TripRequest;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class TripService {
    private final JpaTripRepository tripRepository;

    public Trip save(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip findOneById(Long id) {
        return tripRepository.findById(id).orElseThrow(
                () -> new NotFoundException((ErrorCode.NOT_FOUND), "트립을 찾을 수 없습니다.")
        );
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

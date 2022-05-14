package com.backend.domain.moment.dto;

import com.backend.domain.location.service.LocationService;
import com.backend.domain.moment.domain.entity.Moment;
import com.backend.domain.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MomentMapper {

    private final LocationService locationService;
    private final TripService tripService;

    public Moment toEntity(MomentRequest request) {
        return Moment.builder()
                .location(locationService.findOneById(request.getLocation_id()))
                .trip(tripService.getById(request.getTrip_id()))
                .content(request.getContent())
                .cost(request.getCost())
                .build();
    }
}

package com.backend.domain.moment.dto;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.location.service.LocationService;
import com.backend.domain.moment.domain.entity.Moment;
import com.backend.domain.trip.domain.entity.Trip;
import com.backend.domain.trip.domain.repository.JpaTripRepository;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

import static com.backend.global.error.ErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MomentMapper {

    private final LocationService locationService;
    private final JpaTripRepository tripRepository;

    @Transactional
    public Moment toEntity(MomentRequest request) {
        Location location = Stream.of(request)
                .map(MomentRequest::getLocation_id)
                .map(locationService::findOneById)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "장소를 찾을 수 없습니다."));

        Trip trip = Stream.of(request)
                .map(MomentRequest::getTrip_id)
                .map(tripRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "여행을 찾을 수 없습니다."));

        return Moment.builder()
                .location(location)
                .trip(trip)
                .content(request.getContent())
                .cost(request.getCost())
                .build();
    }

    public MomentResponse fromEntity(Moment moment) {
        return MomentResponse.builder()
                .id(moment.getId())
                .location_id(moment.getLocation().getId())
                .trip_id(moment.getTrip().getId())
                .content(moment.getContent())
                .cost(moment.getCost())
                .build();
    }
}

package com.backend.domain.moment.dto;

import com.backend.domain.location.domain.entity.Location;
import com.backend.domain.location.dto.LocationMapper;
import com.backend.domain.location.service.LocationService;
import com.backend.domain.moment.domain.entity.Moment;
import com.backend.domain.trip.domain.entity.Trip;
import com.backend.domain.trip.service.TripService;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Stream;

import static com.backend.global.error.ErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MomentMapper {

    private final LocationService locationService;
    private final LocationMapper locationMapper;
    private final TripService tripService;

    @Transactional
    public Moment toEntity(MomentRequest request) {
        Location location = Stream.of(request)
                .map(MomentRequest::getLocationId)
                .map(locationService::findById)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "장소를 찾을 수 없습니다."));

        Trip trip = Stream.of(request)
                .map(MomentRequest::getTripId)
                .map(tripService::findById)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "여행을 찾을 수 없습니다."));

        return Moment.builder()
                .location(location)
                .trip(trip)
                .content(request.getContent())
                .cost(request.getCost())
                .sequence(request.getSequence())
                .build();
    }

    public MomentResponseDto fromEntity(Moment moment) {
        return MomentResponseDto.builder()
                .id(moment.getId())
                .location(locationMapper.fromEntity(moment.getLocation()))
                .tripId(moment.getTrip().getId())
                .content(moment.getContent())
                .cost(moment.getCost())
                .sequence(moment.getSequence())
                .build();
    }
}

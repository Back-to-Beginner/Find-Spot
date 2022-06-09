package com.backend.domain.post.service;

import com.backend.domain.moment.service.MomentService;
import com.backend.domain.post.dto.PostMapper;
import com.backend.domain.post.dto.PostResponseDto;
import com.backend.domain.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final TripService tripService;
    private final MomentService momentService;
    private final PostMapper mapper;

    public PostResponseDto getPostByTripId(Long tripId) {
        return mapper.fromTripAndMoments(tripService.findByIdAsDto(tripId), momentService.findAllByTripId(tripId));
    }
}

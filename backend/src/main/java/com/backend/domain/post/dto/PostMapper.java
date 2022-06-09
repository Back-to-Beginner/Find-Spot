package com.backend.domain.post.dto;

import com.backend.domain.moment.dto.MomentMapper;
import com.backend.domain.moment.dto.MomentResponseDto;
import com.backend.domain.trip.dto.TripMapper;
import com.backend.domain.trip.dto.TripResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final TripMapper tripMapper;
    private final MomentMapper momentMapper;

    public PostResponseDto fromTripAndMoments(TripResponseDto tripResponseDto, List<MomentResponseDto> momentResponseDtos) {
        return PostResponseDto.builder()
                .trip(tripResponseDto)
                .moments(momentResponseDtos)
                .build();
    }
}

package com.backend.domain.post.dto;

import com.backend.domain.moment.dto.MomentResponseDto;
import com.backend.domain.trip.dto.TripResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PostResponseDto {

    private TripResponseDto trip;
    private List<MomentResponseDto> moments;

}

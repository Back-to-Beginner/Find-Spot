package com.backend.domain.trip.dto;

import com.backend.domain.tag.domain.entity.Tag;
import com.backend.domain.tag.service.TagService;
import com.backend.domain.trip.domain.entity.Trip;
import com.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripMapper {

    private final UserService userService;

    private final TagService tagService;

    public Trip toEntity(TripRequestDto request) {
        return Trip.builder()
                .user(userService.findUserById(request.getUserId()))
                .title(request.getTitle())
                .beginDate(request.getBeginDate())
                .endDate(request.getEndDate())
                .fullCost(request.getFullCost())
                .regionCode(request.getRegion())
                .tagSet(toTagSet(request.getTagNameList()))
                .build();
    }

    public Set<Tag> toTagSet(List<String> tagList) {
        return tagList.stream()
                .map(tagService::findByName)
                .collect(Collectors.toSet());
    }

    public TripResponseDto fromEntity(Trip trip) {

        Set<String> tagSet = trip.getTagSet()
                .stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        return TripResponseDto.builder()
                .id(trip.getId())
                .userId(trip.getUser().getId())
                .title(trip.getTitle())
                .beginDate(trip.getBeginDate())
                .endDate(trip.getEndDate())
                .fullCost(trip.getFullCost())
                .tags(tagSet)
                .build();
    }
}

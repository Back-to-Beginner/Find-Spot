package com.backend.domain.follow.dto;

import com.backend.domain.follow.domain.entity.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class FollowResponse {

    private Long id;
    private Long userId;
    private Long followingId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static FollowResponse of(Follow follow) {
        return FollowResponse.builder()
                .id(follow.getId())
                .userId(follow.getUser().getId())
                .followingId(follow.getFollowing().getId())
                .createdAt(follow.getCreatedAt())
                .updatedAt(follow.getUpdatedAt())
                .build();
    }
}

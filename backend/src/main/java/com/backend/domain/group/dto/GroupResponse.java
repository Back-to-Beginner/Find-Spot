package com.backend.domain.group.dto;

import com.backend.domain.group.domain.entity.Group;
import com.backend.domain.user.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class GroupResponse {

    private Long id;
    private Set<UserResponse> users;
    private String name;
    private String info;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static GroupResponse of(Group group) {
        return GroupResponse.builder()
                .id(group.getId())
                .users(group.getUsers().stream().map(UserResponse::of).collect(Collectors.toSet()))
                .name(group.getName())
                .info(group.getInfo())
                .createdAt(group.getCreatedAt())
                .updatedAt(group.getUpdatedAt())
                .build();
    }
}

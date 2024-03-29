package com.backend.domain.user.dto;

import com.backend.domain.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private Long groupId;
    private String name;
    private String email;
    private String pw;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .groupId(user.getId() != null ? user.getId() : 0L)
                .email(user.getEmail())
                .pw(user.getPw())
                .build();
    }
}

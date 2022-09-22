package com.backend.domain.follow.dto;

import com.backend.domain.follow.domain.entity.Follow;
import com.backend.domain.user.domain.entity.User;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long followingId;

    public Follow toEntity(User user, User following) {
        return Follow.builder()
                .user(user)
                .following(following)
                .build();
    }
}

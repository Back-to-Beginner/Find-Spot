package com.backend.domain.post.dto;

import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.domain.entity.PostType;
import com.backend.domain.user.domain.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class MissionRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String content;

    public Post toEntity(User user) {
        return Post.builder()
                .type(PostType.MISSION.getType())
                .user(user)
                .content(this.content)
                .build();
    }
}

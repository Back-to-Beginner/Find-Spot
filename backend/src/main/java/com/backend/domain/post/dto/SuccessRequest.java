package com.backend.domain.post.dto;

import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.domain.entity.PostType;
import com.backend.domain.user.domain.entity.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SuccessRequest {
    @NotNull
    private Long parentPostId;

    @NotNull
    private Long userId;

    @NotNull
    private String content;

    public Post toEntity(User user, Post parentPost) {
        return Post.builder()
                .type(PostType.SUCCESS.getType())
                .user(user)
                .parentPost(parentPost)
                .content(this.content)
                .build();
    }

}

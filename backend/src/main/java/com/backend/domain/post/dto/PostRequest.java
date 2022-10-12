package com.backend.domain.post.dto;

import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.user.domain.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class PostRequest {

    @NotNull
    private Character type;

    private Long parentPostId = -1L;

    @NotNull
    private Long userId;

    @NotNull
    private String content;

    public Post toEntity(User user) {
        return Post.builder()
                .type(this.type)
                .user(user)
                .parentPost(null)
                .content(this.content)
                .build();
    }

    public Post toEntity(User user, Post parentPost) {
        return Post.builder()
                .type(this.type)
                .user(user)
                .parentPost(parentPost)
                .content(this.content)
                .build();
    }

}

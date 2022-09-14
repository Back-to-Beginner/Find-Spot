package com.backend.domain.post.dto;

import com.backend.domain.post.domain.entity.Post;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostResponse {

    private Long id;

    private Character type;

    private String content;

    private Long parentPostId;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static PostResponse of(Post post) {

        Long parentPostId = post.getParentPost() == null ? null : post.getParentPost().getId();

        return PostResponse.builder()
                .id(post.getId())
                .type(post.getType())
                .userId(post.getUser().getId())
                .parentPostId(parentPostId)
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

}

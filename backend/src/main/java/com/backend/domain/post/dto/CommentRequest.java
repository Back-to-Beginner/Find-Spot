package com.backend.domain.post.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentRequest {

    @NotNull
    private Long parentPostId;

    @NotNull
    private Long userId;

    @NotNull
    private String content;

}

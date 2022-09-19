package com.backend.domain.post.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProfileRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String content;

}

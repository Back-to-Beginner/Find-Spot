package com.backend.domain.image.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageRequest {
    private String path;
    private Long postId;
}

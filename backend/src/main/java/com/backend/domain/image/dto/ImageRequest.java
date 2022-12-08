package com.backend.domain.image.dto;

import com.backend.domain.image.domain.entity.Image;
import com.backend.domain.post.domain.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageRequest {
    private String path;
    private Long postId;

    public Image toEntity(Post post) {

        return Image.builder()
                .post(post)
                .path(path)
                .build();
    }
}
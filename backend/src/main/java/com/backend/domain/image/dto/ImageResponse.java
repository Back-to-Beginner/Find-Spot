package com.backend.domain.image.dto;

import com.backend.domain.image.domain.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ImageResponse {

    private Long id;
    private Long postId;
    private String path;

    public static ImageResponse of(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .path(image.getPath())
                .postId(image.getPost().getId())
                .build();
    }

}

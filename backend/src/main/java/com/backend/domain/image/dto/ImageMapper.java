package com.backend.domain.image.dto;

import com.backend.domain.image.domain.entity.Image;
import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.service.PostService;
import com.backend.global.domain.MapAble;
import com.backend.global.domain.MapFromPathAble;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageMapper implements
        MapAble<Image, ImageRequest, ImageResponse>,
        MapFromPathAble<Image> {

    private final PostService postService;

    @Override
    public Image toEntity(ImageRequest imageRequest) {
        Post post = postService.getEntity(imageRequest.getPostId());

        return Image.builder()
                .post(post)
                .path(imageRequest.getPath())
                .build();
    }

    @Override
    public ImageResponse fromEntity(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .path(image.getPath())
                .build();
    }

    @Override
    public Image PathToEntity(Long postId, String path) {
        Post post = postService.getEntity(postId);

        return Image.builder()
                .post(post)
                .path(path)
                .build();
    }
}

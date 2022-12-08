package com.backend.domain.image.dto;

import com.backend.domain.image.domain.entity.Image;
import com.backend.domain.post.domain.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageMapper {

    public Image PathToEntity(Post post, String path) {

        return Image.builder()
                .post(post)
                .path(path)
                .build();
    }
}

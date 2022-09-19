package com.backend.global.domain;

import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.dto.PostResponse;

import java.util.List;

public interface FindByTypeAndUserAble <Entity, Response>{
    List<PostResponse> findByTypeAndUser(Character type, Long userId);
    List<Post> findEntityByTypeAndUser(Character type, Long userId);
}

package com.backend.global.domain;

import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.dto.PostResponse;

import java.util.List;

public interface FindByTypeAndParentPostAble<Entity, Response>{
    List<PostResponse> findByTypeAndParentPost(Character type, Long parentPostId);
    List<Post> findEntityByTypeAndParentPost(Character type, Long parentPostId);
}

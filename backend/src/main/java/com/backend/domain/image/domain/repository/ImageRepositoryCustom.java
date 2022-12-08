package com.backend.domain.image.domain.repository;

import com.backend.domain.image.domain.entity.Image;

import java.util.List;

public interface ImageRepositoryCustom {
    List<Image> findEntityByPost(Long postId);
}

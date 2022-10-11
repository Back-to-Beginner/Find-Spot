package com.backend.domain.image.domain.repository;

import com.backend.domain.image.domain.entity.Image;
import com.backend.domain.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByPostOrderByUpdatedAtDesc(Post post);
}

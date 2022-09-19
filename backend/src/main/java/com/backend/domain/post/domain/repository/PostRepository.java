package com.backend.domain.post.domain.repository;

import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM post p WHERE p.type = ?1 AND p.is_deleted = false",
            nativeQuery = true)
    List<Post> findAllByType(Character type);

    @Query(value = "SELECT * FROM post p WHERE p.type = ?1 AND p.user_id = ?2 AND p.is_deleted = false",
            nativeQuery = true)
    List<Post> findAllByTypeAndUser(Character type, User user);

    @Query(value = "SELECT * FROM post p WHERE p.type = ?1 AND p.parent_post_id = ?2 AND p.is_deleted = false",
            nativeQuery = true)
    List<Post> findAllByTypeAndParentPost(Character type, Post parentPost);

    @Query(value = "SELECT * FROM post p WHERE p.type = ?1 AND p.parent_post = ?2 AND p.user AND p.is_deleted = false",
            nativeQuery = true)
    List<Post> findAllByTypeAndParentPostAndUser(Character type, Post parentPost, User user);


}

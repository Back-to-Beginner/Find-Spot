package com.backend.domain.post.domain.entity;

import com.backend.domain.user.domain.entity.User;
import com.backend.global.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
@Table(
        name = "post",
        indexes = @Index(
                name = "idx__type",
                columnList = "type"
        )
)
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Character type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_post_id")
    private Post parentPost;

    private String content;

    private boolean isDeleted = false;

    @Builder
    public Post(Character type, User user, Post parentPost, String content) {
        this.type = type;
        this.user = user;
        this.parentPost = parentPost;
        this.content = content;
    }

    public Post update(Post post){
        this.content = post.content;
        return this;
    }

}
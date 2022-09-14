package com.backend.domain.image.domain.entity;

import com.backend.domain.post.domain.entity.Post;
import com.backend.global.domain.BaseTimeEntity;
import com.backend.global.domain.UpdateEntityAble;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Image
        extends BaseTimeEntity
        implements UpdateEntityAble<Image> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(length = 128, nullable = false)
    private String path;

    private boolean isDeleted = false;

    public void delete() {
        this.isDeleted = true;
    }

    @Builder
    public Image(Post post, String path) {
        this.post = post;
        this.path = path;
    }

    @Override
    public Image update(Image image) {
        this.post = image.getPost();
        this.path = image.getPath();
        return this;
    }
}

package com.backend.domain.collection.domain.entity;

import com.backend.domain.post.domain.entity.Post;
import com.backend.global.domain.BaseTimeEntity;
import com.backend.global.domain.UpdateEntityAble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Collection
        extends BaseTimeEntity
        implements UpdateEntityAble<Collection>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Post> postList = new ArrayList<>();

    private Boolean isDeleted = false;

    @Override
    public Collection update(Collection newEntity) {
        this.name = newEntity.getName();
        this.postList = newEntity.getPostList();
        return this;
    }
}
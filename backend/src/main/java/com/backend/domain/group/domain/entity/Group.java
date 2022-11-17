package com.backend.domain.group.domain.entity;

import com.backend.domain.user.domain.entity.User;
import com.backend.global.domain.BaseTimeEntity;
import com.backend.global.domain.UpdateEntityAble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Group
        extends BaseTimeEntity
        implements UpdateEntityAble<Group>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "group_id")
    private Set<User> users;

    private String name;
    private String info;

    private Boolean isDeleted = false;

    @Builder
    public Group(String name, String info) {
        this.users = new HashSet<>();
        this.name = name;
        this.info = info;
    }

    @Override
    public Group update(Group newEntity) {
        this.name = newEntity.getName();
        this.info = newEntity.getInfo();
        return this;
    }
}
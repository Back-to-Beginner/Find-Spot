package com.backend.domain.group.domain.entity;

import com.backend.domain.user.domain.entity.User;
import com.backend.global.domain.BaseTimeEntity;
import com.backend.global.domain.UpdateEntityAble;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserGroup")
public class Group
        extends BaseTimeEntity
        implements UpdateEntityAble<Group> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "group_id")
    private Set<User> users = new HashSet<>();

    private String name;
    private String info;

    private Boolean isDeleted = false;

    @Builder
    public Group(String name, String info) {
        Random random = new Random();
        this.name = name;
        this.info = info;
    }

    @Override
    public Group update(Group newEntity) {
        this.name = newEntity.getName();
        this.info = newEntity.getInfo();
        return this;
    }

    public Group addUser(User user) {
        this.getUsers().add(user);
        return this;
    }

    public Group deleteUser(User user) {
        if (!this.getUsers().remove(user)) {
            throw new NotFoundException(ErrorCode.NOT_FOUND, "user not found for delete in group");
        }
        return this;
    }
}
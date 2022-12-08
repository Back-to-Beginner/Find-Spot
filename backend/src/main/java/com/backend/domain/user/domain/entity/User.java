package com.backend.domain.user.domain.entity;

import com.backend.domain.group.domain.entity.Group;
import com.backend.global.domain.BaseTimeEntity;
import com.backend.global.domain.UpdateEntityAble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User
        extends BaseTimeEntity
        implements UpdateEntityAble<User>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String pw;

    private boolean isDeleted = false;

    @Builder
    public User(String name, String email, String pw) {
        this.name = name;
        this.email = email;
        this.pw = pw;
        this.isDeleted = false;
    }

    @Override
    public User update(User newEntity) {
        this.name = newEntity.getName();
        this.pw = newEntity.getPw();
        this.email = newEntity.getEmail();
        return this;
    }

    public User joinGroup(Group group) {
        this.group = group;
        return this;
    }

    public User quitGroup() {
        this.group = null;
        return this;
    }
}
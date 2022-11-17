package com.backend.domain.user.domain.entity;

import com.backend.global.domain.BaseTimeEntity;
import com.backend.global.domain.UpdateEntityAble;
import lombok.*;

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

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String pw;

    private Boolean isDeleted = false;

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
}
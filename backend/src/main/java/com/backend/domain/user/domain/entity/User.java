package com.backend.domain.user.domain.entity;

import com.backend.global.domain.basetime.domain.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String pw;

    @Builder
    public User(String name, String email, String pw){
        this.name = name;
        this.email = email;
        this.pw = pw;
    }


}
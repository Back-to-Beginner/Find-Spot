package com.backend.domain.user.domain.entity;

import com.backend.global.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String pw;

    @Builder
    public User(String name, String email, String pw){
        this.name = name;
        this.email = email;
        this.pw = pw;
    }


}
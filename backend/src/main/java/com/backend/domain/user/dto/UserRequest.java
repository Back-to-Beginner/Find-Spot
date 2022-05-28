package com.backend.domain.user.dto;

import com.backend.domain.user.domain.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String pw;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .pw(this.pw)
                .build();
    }
}

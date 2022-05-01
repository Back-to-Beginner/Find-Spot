package com.backend.domain.user.dto;

import com.backend.domain.user.domain.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {

//    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String pw;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .pw(this.pw)
                .build();
    }
}

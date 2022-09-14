package com.backend.domain.user.dto;

import com.backend.domain.user.domain.entity.User;
import com.backend.global.domain.MapAble;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements
        MapAble<User, UserRequest, UserResponse> {

    @Override
    public User toEntity(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse fromEntity(User user) {
        return null;
    }
}

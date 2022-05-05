package com.backend.domain.user.service;

import com.backend.domain.user.domain.entity.User;
import com.backend.domain.user.domain.repository.JpaUserRepository;
import com.backend.global.error.NotFoundException;
import com.backend.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final JpaUserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "User Not Found")
        );
    }

}
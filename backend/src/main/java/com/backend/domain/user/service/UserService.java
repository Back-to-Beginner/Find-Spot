package com.backend.domain.user.service;

import com.backend.domain.user.domain.entity.User;
import com.backend.domain.user.domain.repository.UserRepository;
import com.backend.domain.user.dto.LoginRequest;
import com.backend.global.error.NotFoundException;
import com.backend.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public User createUser(User user) {
        return repository.save(user);
    }

    public List<User> findAllUser() {
        return repository.findAll();
    }

    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "User Not Found")
        );
    }

    public User login(LoginRequest request) {
        return repository.findByEmailAndAndPw(request.getEmail(), request.getPw());
    }

}
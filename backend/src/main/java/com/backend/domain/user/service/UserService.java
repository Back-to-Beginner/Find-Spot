package com.backend.domain.user.service;

import com.backend.domain.post.service.PostService;
import com.backend.domain.user.domain.entity.User;
import com.backend.domain.user.domain.repository.UserRepository;
import com.backend.domain.user.dto.LoginRequest;
import com.backend.domain.user.dto.UserRequest;
import com.backend.domain.user.dto.UserResponse;
import com.backend.global.domain.CrudAble;
import com.backend.global.domain.FindEntityAble;
import com.backend.global.domain.GetEntityAble;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements
        CrudAble<UserRequest, UserResponse>,
        GetEntityAble<User>,
        FindEntityAble<User> {

    private final UserRepository repository;
    private final PostService postService;

    public UserResponse login(LoginRequest request) {
        User user = repository.findByEmailAndAndPw(request.getEmail(), request.getPw())
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, "로그인 정보가 일치하지 않습니다."));
        return UserResponse.of(user);
    }

    @Override
    @Transactional
    public UserResponse save(UserRequest userRequest) {
        User user = repository.save(userRequest.toEntity());
        postService.createProfile(user.getId());
        return UserResponse.of(user);
    }

    @Override
    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(long id) {
        User user = repository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "User Not Found")
        );
        return UserResponse.of(user);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public UserResponse update(long id, UserRequest userRequest) {
        User newUser = userRequest.toEntity();
        return UserResponse.of(findEntity(id).update(newUser));
    }

    @Override
    public User findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "User Not Found")
        );
    }

    @Override
    public User getEntity(Long id) {
        return repository.getById(id);
    }

}
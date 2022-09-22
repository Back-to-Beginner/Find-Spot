package com.backend.domain.follow.service;

import com.backend.domain.follow.domain.entity.Follow;
import com.backend.domain.follow.domain.repository.FollowRepository;
import com.backend.domain.follow.dto.FollowRequest;
import com.backend.domain.follow.dto.FollowResponse;
import com.backend.domain.user.domain.entity.User;
import com.backend.domain.user.service.UserService;
import com.backend.global.domain.CrudAble;
import com.backend.global.domain.FindEntityAble;
import com.backend.global.domain.GetEntityAble;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService implements
        CrudAble<FollowRequest, FollowResponse>,
        GetEntityAble<Follow>,
        FindEntityAble<Follow> {

    private final FollowRepository repository;
    private final UserService userService;

    @Override
    public FollowResponse save(FollowRequest followRequest) {
        User user = userService.findEntity(followRequest.getUserId());
        User following = userService.findEntity(followRequest.getFollowingId());
        Follow follow = repository.save(followRequest.toEntity(user, following));
        return FollowResponse.of(follow);
    }

    @Override
    public List<FollowResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(FollowResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public FollowResponse findById(long id) {
        Follow follow = repository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "User Not Found")
        );
        return FollowResponse.of(follow);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public FollowResponse update(long id, FollowRequest followRequest) {
        User user = userService.findEntity(followRequest.getUserId());
        User following = userService.findEntity(followRequest.getFollowingId());
        Follow newFollow = followRequest.toEntity(user, following);
        return FollowResponse.of(findEntity(id).update(newFollow));
    }

    @Override
    public Follow findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND, "User Not Found")
        );
    }

    @Override
    public Follow getEntity(Long id) {
        return repository.getById(id);
    }
}
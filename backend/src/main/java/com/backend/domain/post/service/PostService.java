package com.backend.domain.post.service;

import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.domain.entity.PostType;
import com.backend.domain.post.domain.repository.PostRepository;
import com.backend.domain.post.dto.*;
import com.backend.domain.user.domain.entity.User;
import com.backend.domain.user.service.UserService;
import com.backend.global.domain.*;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService implements
        CrudAble<PostRequest, PostResponse>,
        GetEntityAble<Post>,
        FindEntityAble<Post>,
        FindByTypeAble<Post, PostResponse, Character>,
        FindByTypeAndUserAble<Post, PostResponse>,
        FindByTypeAndParentPostAble<Post, PostResponse>,
        ExistEntityAble {

    private final PostRepository repository;
    private final UserService userService;

    @Override
    public List<PostResponse> findAll() {
        return repository.findAll()
                .stream()
                .parallel()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse save(PostRequest request) {
        User user = userService.getEntity(request.getUserId());

        Post post;
        if (request.getParentPostId() == -1L) {
            post = request.toEntity(user);
        } else {
            post = request.toEntity(user, findEntity(request.getParentPostId()));
        }
        return PostResponse.of(repository.save(post));

    }

    public PostResponse saveMission(MissionRequest request) {
        User user = userService.findEntity(request.getUserId());


        Post post = request.toEntity(user);
        return PostResponse.of(repository.save(post));
    }

    public PostResponse saveSuccess(SuccessRequest request) {
        User user = userService.findEntity(request.getUserId());
        Post parentPost = findEntity(request.getParentPostId());
        checkParentTypeAvailable(parentPost.getType(), PostType.SUCCESS);

        Post post = request.toEntity(user, parentPost);
        return PostResponse.of(repository.save(post));
    }

    @Override
    public PostResponse findById(long id) {
        return PostResponse.of(findEntity(id));
    }

    @Override
    public void deleteById(long id) {
        repository.delete(findEntity(id));
    }

    @Override
    public PostResponse update(long id, PostRequest postRequest) {
        User user = userService.findEntity(postRequest.getUserId());

        Post newPost = postRequest.toEntity(user);
        return PostResponse.of(findEntity(id).update(newPost));
    }

    @Override
    public Post getEntity(Long id) {
        return repository.getById(id);
    }

    @Override
    public Post findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, "모먼트를 찾을 수 없습니다."));
    }

    @Override
    public List<PostResponse> findByType(Character character) {
        return findEntityByType(character)
                .stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findEntityByType(Character character) {
        return repository.findAllByType(character);
    }

    @Override
    public List<PostResponse> findByTypeAndUser(Character type, Long userId) {
        return findEntityByTypeAndUser(type, userId)
                .stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findEntityByTypeAndUser(Character character, Long userId) {
        User user = userService.getEntity(userId);
        return repository.findAllByTypeAndUser(character, user);
    }

    @Override
    public List<PostResponse> findByTypeAndParentPost(Character type, Long parentPostId) {
        return findEntityByTypeAndParentPost(type, parentPostId)
                .stream()
                .map(PostResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findEntityByTypeAndParentPost(Character type, Long parentPostId) {
        Post parentPost = getEntity(parentPostId);
        return repository.findAllByTypeAndParentPost(type, parentPost);
    }

    private void checkParentTypeAvailable(Character parentType, PostType postType) {
        if (!postType.getParentType().contains(parentType))
            throw new RuntimeException("parent type is not available type");
    }

    @Override
    public boolean isExist(Long id) {
        return repository.existsById(id);
    }
}

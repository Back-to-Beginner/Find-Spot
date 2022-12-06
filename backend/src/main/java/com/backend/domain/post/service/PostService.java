package com.backend.domain.post.service;

import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.domain.entity.PostType;
import com.backend.domain.post.domain.repository.PostRepository;
import com.backend.domain.post.dto.CardResponse;
import com.backend.domain.post.dto.PostRequest;
import com.backend.domain.post.dto.PostResponse;
import com.backend.domain.user.domain.entity.User;
import com.backend.domain.user.service.UserService;
import com.backend.global.domain.CrudAble;
import com.backend.global.domain.ExistEntityAble;
import com.backend.global.domain.FindEntityAble;
import com.backend.global.domain.GetEntityAble;
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
            checkParentTypeAvailable(post.getType());
        } else {
            post = request.toEntity(user, findEntity(request.getParentPostId()));
            checkParentTypeAvailable(post.getParentPost().getType(), post.getType());
        }

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

    public CardResponse findCard(Long id) {
        return repository.findCard(id);
    }

    public List<CardResponse> findByType(Character character) {
        return repository.findCardByType(character);

    }

    public List<CardResponse> findByTypeAndUser(Character type, Long userId) {
        return repository.findCardByTypeAndUser(type, userId);
    }

    public List<CardResponse> findTypeAndParentPost(Character type, Long parentPostId) {
        return repository.findCardByTypeAndParentPost(type, parentPostId);
    }

    private void checkParentTypeAvailable(Character parentType, Character postType) {
        PostType type = getPostType(postType);
        if (!type.getParentType().contains(parentType))
            throw new NotFoundException(ErrorCode.BAD_REQUEST, "부모 타입이 옳바르지 않습니다.");
    }

    private void checkParentTypeAvailable(Character postType) {
        PostType type = getPostType(postType);
        if (!type.getParentType().isEmpty())
            throw new NotFoundException(ErrorCode.BAD_REQUEST, "부모 타입이 옳바르지 않습니다.");
    }

    private PostType getPostType(Character postType) {
        PostType type;
        if (PostType.SUCCESS.getType() == postType) {
            type = PostType.SUCCESS;
        } else if (PostType.MISSION.getType() == postType) {
            type = PostType.MISSION;
        } else if (PostType.COMMENT.getType() == postType) {
            type = PostType.COMMENT;
        } else if (PostType.PROFILE.getType() == postType) {
            type = PostType.PROFILE;
        } else {
            throw new NotFoundException(ErrorCode.NOT_FOUND, "타입이 옳바르지 않습니다.");
        }
        return type;
    }

    public List<CardResponse> search(char type, String word) {
        return repository.searchCard(type, word);
    }

    @Override
    public boolean isExist(Long id) {
        return repository.existsById(id);
    }
}

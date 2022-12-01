package com.backend.domain.post.service;

import com.backend.domain.image.domain.entity.QImage;
import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.domain.entity.PostType;
import com.backend.domain.post.domain.entity.QPost;
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
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    private final JPAQueryFactory queryFactory;
    private static final QPost qPost = QPost.post;
    private static final QImage qImage = QImage.image;
    private BooleanBuilder booleanBuilder;

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
        booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPost.id.eq(id));
        return queryFactory
                .select(
                        Projections.constructor(
                                CardResponse.class,
                                qPost.id,
                                qPost.type,
                                qPost.content,
                                qPost.parentPost.id,
                                qPost.user.id,
                                qPost.user.name.as("userName"),
                                qImage.path,
                                qPost.createdAt,
                                qPost.updatedAt
                        )
                )
                .from(qImage)
                .innerJoin(qImage.post, qPost)
                .where(booleanBuilder)
                .fetchFirst();
    }

    public List<CardResponse> findByType(Character character) {
        return findEntityByType(character);

    }

    public List<CardResponse> findEntityByType(Character character) {
        booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPost.type.eq(character));
        booleanBuilder.and(qPost.isDeleted.eq(false));
        booleanBuilder.and(qImage.isDeleted.eq(false));

        return queryFactory
                .select(
                        Projections.constructor(
                                CardResponse.class,
                                qPost.id,
                                qPost.type,
                                qPost.content,
                                qPost.parentPost.id,
                                qPost.user.id,
                                qPost.user.name.as("userName"),
                                qImage.path,
                                qPost.createdAt,
                                qPost.updatedAt
                        )
                )
                .from(qImage)
                .innerJoin(qImage.post, qPost)
                .where(booleanBuilder)
                .orderBy(qPost.createdAt.desc())
                .fetch();
    }

    public List<CardResponse> findByTypeAndUser(Character type, Long userId) {
        return findEntityByTypeAndUser(type, userId);
    }

    public List<CardResponse> findEntityByTypeAndUser(Character type, Long userId) {
        booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPost.type.eq(type));
        booleanBuilder.and(qPost.isDeleted.eq(false));
        booleanBuilder.and(qPost.user.id.eq(userId));
        booleanBuilder.and(qImage.isDeleted.eq(false));

        return queryFactory.select(Projections.constructor(
                        CardResponse.class,
                        qPost.id,
                        qPost.type,
                        qPost.content,
                        qPost.parentPost.id,
                        qPost.user.id,
                        qPost.user.name.as("userName"),
                        qImage.path,
                        qPost.createdAt,
                        qPost.updatedAt
                ))
                .from(qImage)
                .innerJoin(qImage.post, qPost)
                .where(booleanBuilder)
                .orderBy(qPost.createdAt.desc())
                .fetch();
    }

    public List<CardResponse> findTypeAndParentPost(Character type, Long parentPostId) {
        if (type == 'c') return findEntityByTypeAndParentPost(parentPostId);
        return findEntityByTypeAndParentPost(type, parentPostId);
    }

    public List<CardResponse> findEntityByTypeAndParentPost(Character type, Long parentPostId) {
        booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPost.type.eq(type));
        booleanBuilder.and(qPost.isDeleted.eq(false));
        booleanBuilder.and(qPost.parentPost.id.eq(parentPostId));
        booleanBuilder.and(qPost.parentPost.isDeleted.eq(false));
        booleanBuilder.and(qImage.isDeleted.eq(false));

        return queryFactory.select(Projections.constructor(
                        CardResponse.class,
                        qPost.id,
                        qPost.type,
                        qPost.content,
                        qPost.parentPost.id,
                        qPost.user.id,
                        qPost.user.name.as("userName"),
                        qImage.path,
                        qPost.createdAt,
                        qPost.updatedAt
                ))
                .from(qImage)
                .where(booleanBuilder)
                .orderBy(qPost.createdAt.desc())
                .fetch();
    }

    public List<CardResponse> findEntityByTypeAndParentPost(Long parentPostId) {
        booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPost.type.eq('c'));
        booleanBuilder.and(qPost.isDeleted.eq(false));
        booleanBuilder.and(qPost.parentPost.id.eq(parentPostId));
        booleanBuilder.and(qPost.parentPost.isDeleted.eq(false));

        return queryFactory.select(Projections.constructor(
                        CardResponse.class,
                        qPost.id,
                        qPost.type,
                        qPost.content,
                        qPost.parentPost.id,
                        qPost.user.id,
                        qPost.user.name.as("userName"),
                        qPost.content.as("imagePath"),
                        qPost.createdAt,
                        qPost.updatedAt
                ))
                .from(qPost)
                .where(booleanBuilder)
                .orderBy(qPost.createdAt.desc())
                .fetch();
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
        return searchEntity(type, word);
    }

    private List<CardResponse> searchEntity(char type, String word) {
        booleanBuilder = new BooleanBuilder();
        if (type == 'u' && !word.equals("*")) booleanBuilder.and(qPost.user.name.contains(word));
        else if (!word.equals("*")) booleanBuilder.and(qPost.content.contains(word));
        booleanBuilder.and(qPost.type.eq(type));
        booleanBuilder.and(qPost.isDeleted.eq(false));
        booleanBuilder.and(qImage.isDeleted.eq(false));

        return queryFactory.select(Projections.constructor(
                        CardResponse.class,
                        qPost.id,
                        qPost.type,
                        qPost.content,
                        qPost.parentPost.id,
                        qPost.user.id,
                        qPost.user.name.as("userName"),
                        qImage.path,
                        qPost.createdAt,
                        qPost.updatedAt
                ))
                .from(qImage)
                .innerJoin(qImage.post, qPost)
                .where(booleanBuilder)
                .orderBy(qPost.createdAt.desc())
                .fetch();
    }

    @Override
    public boolean isExist(Long id) {
        return repository.existsById(id);
    }
}

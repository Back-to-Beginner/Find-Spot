package com.backend.domain.post.domain.repository;

import com.backend.domain.image.domain.entity.QImage;
import com.backend.domain.post.domain.entity.Post;
import com.backend.domain.post.domain.entity.QPost;
import com.backend.domain.post.dto.CardResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryCustom {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public PostRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Post.class);
        queryFactory = jpaQueryFactory;
    }

    private final JPAQueryFactory queryFactory;
    private static final QPost qPost = QPost.post;
    private static final QImage qImage = QImage.image;
    private BooleanBuilder booleanBuilder;

    @Override
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

    @Override
    public List<CardResponse> findCardByType(Character type) {
        booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPost.type.eq(type));
        booleanBuilder.and(qPost.isDeleted.eq(false));
        booleanBuilder.and(qImage.isDeleted.eq(false));

        return queryCardList(booleanBuilder);
    }

    @Override
    public List<CardResponse> findCardByTypeAndUser(Character type, Long userId) {
        booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPost.type.eq(type));
        booleanBuilder.and(qPost.isDeleted.eq(false));
        booleanBuilder.and(qPost.user.id.eq(userId));
        booleanBuilder.and(qImage.isDeleted.eq(false));

        return queryCardList(booleanBuilder);
    }

    @Override
    public List<CardResponse> findCardByTypeAndParentPost(Character type, Long parentPostId) {
        booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPost.type.eq(type));
        booleanBuilder.and(qPost.isDeleted.eq(false));
        booleanBuilder.and(qPost.parentPost.id.eq(parentPostId));
        booleanBuilder.and(qPost.parentPost.isDeleted.eq(false));
        if (type != 'c') {
            booleanBuilder.and(qImage.isDeleted.eq(false));
            return queryCardList(booleanBuilder);
        }

        return queryCardList(booleanBuilder);
    }

    @Override
    public List<CardResponse> searchCard(Character type, String searchWord) {
        booleanBuilder = new BooleanBuilder();
        if (type == 'u' && !searchWord.equals("*")) booleanBuilder.and(qPost.user.name.contains(searchWord));
        else if (!searchWord.equals("*")) booleanBuilder.and(qPost.content.contains(searchWord));
        booleanBuilder.and(qPost.type.eq(type));
        booleanBuilder.and(qPost.isDeleted.eq(false));
        booleanBuilder.and(qImage.isDeleted.eq(false));

        return queryCardList(booleanBuilder);
    }

    private List<CardResponse> queryCardList(BooleanBuilder booleanBuilder){
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
}

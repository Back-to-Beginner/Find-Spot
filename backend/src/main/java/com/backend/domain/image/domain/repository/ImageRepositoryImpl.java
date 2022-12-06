package com.backend.domain.image.domain.repository;

import com.backend.domain.image.domain.entity.Image;
import com.backend.domain.image.domain.entity.QImage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ImageRepositoryImpl extends QuerydslRepositorySupport implements ImageRepositoryCustom {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public ImageRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Image.class);
        queryFactory = jpaQueryFactory;
    }

    private final JPAQueryFactory queryFactory;
    private static final QImage qImage = QImage.image;

    @Override
    public List<Image> findEntityByPost(Long postId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qImage.post.id.eq(postId));
        booleanBuilder.and(qImage.isDeleted.eq(false));

        return queryFactory.selectFrom(qImage)
                .where(booleanBuilder)
                .orderBy(qImage.updatedAt.desc())
                .fetch();
    }
}

package com.backend.domain.group.domain.repository;

import com.backend.domain.group.domain.entity.Group;
import com.backend.domain.group.domain.entity.QGroup;
import com.backend.domain.user.domain.entity.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GroupRepositoryImpl extends QuerydslRepositorySupport implements GroupRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QGroup qGroup = QGroup.group;
    private final QUser qUser = QUser.user;
    private BooleanBuilder booleanBuilder;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param domainClass must not be {@literal null}.
     */
    public GroupRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Group.class);
        queryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Group> findGroupByUserId(Long userId) {
        booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUser.id.eq(userId));
        booleanBuilder.and(qGroup.isDeleted.eq(false));

        return Optional.ofNullable(queryFactory
                .selectFrom(qGroup)
                .innerJoin(qGroup.users, qUser)
                .where(booleanBuilder)
                .fetchFirst());
    }
}

package com.backend.domain.group.domain.repository;

import com.backend.domain.group.domain.entity.Group;
import com.backend.domain.group.domain.entity.QGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepositoryImpl extends QuerydslRepositorySupport implements GroupRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QGroup qGroup = QGroup.group;
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

}

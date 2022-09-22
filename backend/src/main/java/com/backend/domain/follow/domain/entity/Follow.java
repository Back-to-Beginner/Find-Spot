package com.backend.domain.follow.domain.entity;

import com.backend.domain.user.domain.entity.User;
import com.backend.global.domain.BaseTimeEntity;
import com.backend.global.domain.UpdateEntityAble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Follow
        extends BaseTimeEntity
        implements UpdateEntityAble<Follow>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "following_user_id")
    private User following;

    private Boolean isDeleted = false;

    @Builder
    public Follow(User user, User following) {
        this.user = user;
        this.following = following;
        this.isDeleted = false;
    }

    @Override
    public Follow update(Follow newEntity) {
        this.user = newEntity.getUser();
        this.following = newEntity.getFollowing();
        return this;
    }
}
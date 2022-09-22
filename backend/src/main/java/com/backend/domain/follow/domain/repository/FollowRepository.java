package com.backend.domain.follow.domain.repository;

import com.backend.domain.follow.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query(value = "SELECT * FROM user u WHERE u.id IN (SELECT f.following_user_id FROM follow f WHERE f.user_id = ?1)",
            nativeQuery = true)
    List<Follow> findFollowingUserByUserId(Long userId);

    @Query(value = "",
            nativeQuery = true)
    List<Follow> findUserByFollowingId(Long followingId);
}

package com.backend.domain.follow.controller;

import com.backend.global.dto.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface FollowController {

    ApiResponse findUserByFollowingId(@PathVariable Long followingId);
    //나를 팔로우 한 사람 찾기

    ApiResponse findFollowingByUserId(@PathVariable Long userID);
    //내가 팔로우 한 사람 찾기
}

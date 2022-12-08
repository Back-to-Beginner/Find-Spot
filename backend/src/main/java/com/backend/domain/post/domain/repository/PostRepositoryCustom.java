package com.backend.domain.post.domain.repository;

import com.backend.domain.post.dto.CardResponse;

import java.util.List;

public interface PostRepositoryCustom {
    CardResponse findCard(Long id);
    List<CardResponse> findCardByType(Character type);
    List<CardResponse> findCardByTypeAndUser(Character type, Long userId);
    List<CardResponse> findCardByTypeAndParentPost(Character type, Long parentPostId);
    List<CardResponse> searchCard(Character type, String searchWord);
    List<CardResponse> findCardByUserGroup(Character type, Long groupId);

}

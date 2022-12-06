package com.backend.domain.group.domain.repository;

import com.backend.domain.group.domain.entity.Group;

import java.util.Optional;

public interface GroupRepositoryCustom {
    Optional<Group> findGroupByUserId(Long userId);

}

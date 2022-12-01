package com.backend.domain.group.domain.repository;

import com.backend.domain.group.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {}
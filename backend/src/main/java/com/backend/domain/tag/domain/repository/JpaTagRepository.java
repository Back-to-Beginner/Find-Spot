package com.backend.domain.tag.domain.repository;

import com.backend.domain.tag.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTagRepository extends JpaRepository<Tag, Long> {
}

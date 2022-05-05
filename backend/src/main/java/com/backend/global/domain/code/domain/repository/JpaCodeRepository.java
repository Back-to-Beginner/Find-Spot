package com.backend.global.domain.code.domain.repository;

import com.backend.domain.tag.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCodeRepository extends JpaRepository<Tag, Long> {
}

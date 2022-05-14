package com.backend.domain.tag.domain.repository;

import com.backend.domain.tag.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByNameLike(String name);
    Tag findByName(String name);

    boolean existsByName(String name);

}

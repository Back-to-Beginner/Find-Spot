package com.backend.domain.tag.domain.repository;

import com.backend.domain.tag.domain.entity.TagTripMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTagTripMapperRepository extends JpaRepository<TagTripMapper, Long> {
}

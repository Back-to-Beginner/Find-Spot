package com.backend.domain.image.domain.repository;

import com.backend.domain.image.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaImageRepository extends JpaRepository<Image, Long> {
    public List<Image> findAllByLocationId(Long location_id);
}

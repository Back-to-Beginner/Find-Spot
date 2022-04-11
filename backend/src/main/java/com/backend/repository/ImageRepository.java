package com.backend.repository;

import com.backend.entity.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    Image save(Image image);

    Optional<Image> findById(Long id);

    List<Image> findByLocationId(Long location_id);

    List<Image> findAll();

}

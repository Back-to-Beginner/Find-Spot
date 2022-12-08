package com.backend.domain.image.domain.repository;

import com.backend.domain.image.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long>, ImageRepositoryCustom{
}

package com.backend.domain.collection.domain.repository;

import com.backend.domain.collection.domain.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {}

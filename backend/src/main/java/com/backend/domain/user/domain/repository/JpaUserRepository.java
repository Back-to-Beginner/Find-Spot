package com.backend.domain.user.domain.repository;

import com.backend.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
}

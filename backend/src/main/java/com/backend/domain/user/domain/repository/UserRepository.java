package com.backend.domain.user.domain.repository;

import com.backend.domain.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndAndPw(String email, String pw);
    List<User> findByGroup(Long groupId);
    Optional<User> findByIdAndGroup(Long id, Long groupId);
}

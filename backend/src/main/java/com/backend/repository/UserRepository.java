package com.backend.repository;

import java.util.List;
import java.util.Optional;

import com.backend.entity.User;

public interface UserRepository {
	User save(User user);

	Optional<User> findById(Long id);

	Optional<User> findByName(String name);

	List<User> findAll();
}
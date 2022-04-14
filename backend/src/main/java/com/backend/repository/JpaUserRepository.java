package com.backend.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.backend.entity.User;

public class JpaUserRepository implements UserRepository {

	private final EntityManager em;

	public JpaUserRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public User save(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public Optional<User> findById(Long id) {
		User user = em.find(User.class, id);
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> findByName(String name) {
		List<User> result = em.createQuery("select m from User m where m.name = :name", User.class)
			.setParameter("name", name)
			.getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<User> findAll() {
		return em.createQuery("select m from User m", User.class)
			.getResultList();
	}
}


package com.backend.config;

import com.backend.service.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.backend.repository.JpaUserRepository;
import com.backend.repository.UserRepository;

@Configuration
public class SpringConfig {
	private final DataSource dataSource;
	private final EntityManager em;

	public SpringConfig(DataSource dataSource, EntityManager em) {
		this.dataSource = dataSource;
		this.em = em;
	}

	@Bean
	public UserService userService() {
		return new UserService((JpaUserRepository)userRepository());
	}

	@Bean
	public UserRepository userRepository() {
		return new JpaUserRepository(em);
	}
}

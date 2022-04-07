package com.backend.config;

import com.backend.service.UserService;
import com.backend.user.JpaUserRepository;
import com.backend.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

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
    return new UserService((JpaUserRepository) userRepository());
  }

  @Bean
  public UserRepository userRepository() {
    return new JpaUserRepository(em);
  }
}

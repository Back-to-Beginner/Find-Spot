package com.backend.domain.user.service;

import com.backend.domain.user.domain.entity.User;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(JpaUserRepository jpaUserRepository) {
        this.userRepository = jpaUserRepository;
    }

    //로그인
    public boolean login() {

        return false;
    }

    // 회원 가입
    public Long join(User user) {
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateMember(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<User> findMembers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long memberId) {
        return userRepository.findById(memberId);
    }
}
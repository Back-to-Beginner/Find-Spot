package com.backend.global.domain.code.domain.repository;

import com.backend.global.domain.code.domain.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCodeRepository extends JpaRepository<Code, Long> {

    boolean existsById(Long id);

    Optional<Code> findCodeByCode(String code);

    Optional<Code> findCodeByMemo(String code);
}

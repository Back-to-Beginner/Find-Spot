package com.backend.global.domain.code.service;

import com.backend.global.domain.code.domain.entity.Code;
import com.backend.global.domain.code.domain.repository.JpaCodeRepository;
import com.backend.global.domain.code.dto.CodeMapper;
import com.backend.global.domain.code.dto.CodeRequestDto;
import com.backend.global.domain.code.dto.CodeResponseDto;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.backend.global.error.ErrorCode.BAD_REQUEST;
import static com.backend.global.error.ErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CodeService {
    private final JpaCodeRepository repository;

    private final CodeMapper mapper;

    public CodeResponseDto save(CodeRequestDto entity) {
        return Stream.of(entity)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(BAD_REQUEST, "코드를 저장할 수 없습니다."));
    }

    public List<CodeResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public CodeResponseDto findById(long id) {
        return Stream.of(id)
                .map(repository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "코드를 조회할 수 없습니다."));
    }

    @Transactional
    public Code getById(long id) {
        return Stream.of(id)
                .map(repository::getById)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "코드을 조회할 수 없습니다."));
    }

    public void deleteById(long id) {
        Stream.of(id)
                .map(this::getById)
                .forEach(repository::delete);
    }

    public CodeResponseDto updateById(long id, CodeRequestDto request) {
        return Stream.of(id)
                .map(this::getById)
                .map(code -> code.update(mapper.toEntity(request)))
                .map(repository::save)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(BAD_REQUEST, "코드를 갱신할 수 없습니다."));
    }

    public CodeResponseDto findByCode(String code) {
        return Stream.of(code)
                .map(repository::findCodeByCode)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(BAD_REQUEST, "코드를 코드로 찾을 수 없습니다."));
    }

    public Code findByMemo(String code) {
        return Stream.of(code)
                .map(repository::findCodeByMemo)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .orElseThrow(() -> new NotFoundException(BAD_REQUEST, "코드를 코드로 찾을 수 없습니다."));
    }



}

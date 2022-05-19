package com.backend.global.domain.code.dto;

import com.backend.global.domain.code.domain.entity.Code;
import com.backend.global.domain.code.domain.repository.JpaCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CodeMapper {

    private final JpaCodeRepository repository;

    public Code toEntity(CodeRequestDto request) {
        Code parentCode = Stream.of(request)
                .map(CodeRequestDto::getParentCode)
                .map(repository::findCodeByCode)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .orElse(null);

        return Code.builder()
                .code(request.getCode())
                .memo(request.getMemo())
                .parent(parentCode)
                .build();
    }

    public CodeResponseDto fromEntity(Code entity) {
        if (entity.getParent() == null) {
            return CodeResponseDto.builder()
                    .id(entity.getId())
                    .code(entity.getCode())
                    .memo(entity.getMemo())
                    .parent_code(null)
                    .build();
        }
        return CodeResponseDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .memo(entity.getMemo())
                .parent_code(fromEntity(entity.getParent()))
                .build();
    }
}

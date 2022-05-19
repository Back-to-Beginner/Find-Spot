package com.backend.global.domain.code.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CodeResponseDto {
    private Long id;
    private String code;
    private String memo;
    private CodeResponseDto parent_code;
}

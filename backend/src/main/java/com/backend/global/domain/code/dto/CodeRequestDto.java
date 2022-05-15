package com.backend.global.domain.code.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CodeRequestDto {

    @NotNull
    private String code;

    @NotNull
    private String memo;

    @NotNull
    private String parentCode;

}

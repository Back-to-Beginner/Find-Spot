package com.backend.domain.tag.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TagRequestDto {
    @NotNull
    private String name;
}

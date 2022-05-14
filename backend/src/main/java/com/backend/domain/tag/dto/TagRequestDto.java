package com.backend.domain.tag.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class TagRequestDto {
    @NotNull
    private String name;
}

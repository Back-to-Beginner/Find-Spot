package com.backend.domain.tag.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagResponseDto {
    private long id;
    private String name;
}

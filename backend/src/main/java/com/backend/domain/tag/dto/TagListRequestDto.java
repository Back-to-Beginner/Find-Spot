package com.backend.domain.tag.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class TagListRequestDto {
    @NotNull
    private List<String> nameList;
}

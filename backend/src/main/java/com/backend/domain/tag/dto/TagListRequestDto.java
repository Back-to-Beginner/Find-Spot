package com.backend.domain.tag.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TagListRequestDto {

    @NotNull
    private List<String> nameList;
}

package com.backend.domain.tag.dto;

import com.backend.domain.tag.domain.entity.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagMapper {

    public Tag toEntity(TagRequestDto tagRequestDto) {
        return Tag.builder()
                .name(tagRequestDto.getName())
                .build();
    }

    public TagResponseDto fromEntity(Tag tag) {
        return TagResponseDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}

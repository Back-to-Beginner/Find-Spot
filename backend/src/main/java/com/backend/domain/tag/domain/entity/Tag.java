package com.backend.domain.tag.domain.entity;

import com.backend.domain.tag.dto.TagRequestDto;
import com.backend.global.domain.basetime.domain.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Tag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Builder
    public Tag(String name) {
        this.name = name;
    }

    public Tag update(TagRequestDto tagRequestDto){
        this.name = tagRequestDto.getName();
        return this;
    }

}

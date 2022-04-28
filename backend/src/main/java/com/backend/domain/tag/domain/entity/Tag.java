package com.backend.domain.tag.domain.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Tag {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String tag;

}

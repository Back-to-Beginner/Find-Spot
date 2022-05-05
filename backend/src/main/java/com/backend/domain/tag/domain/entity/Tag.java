package com.backend.domain.tag.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

}

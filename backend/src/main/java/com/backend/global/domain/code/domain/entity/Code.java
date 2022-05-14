package com.backend.global.domain.code.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String memo;

    @ManyToOne
    @JoinColumn(name = "parent_code_id")
    private Code parentCode;

}

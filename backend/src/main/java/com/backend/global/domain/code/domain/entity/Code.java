package com.backend.global.domain.code.domain.entity;

import com.backend.global.domain.basetime.domain.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Code extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(unique = true, nullable = false)
    private String memo;

    @ManyToOne
    @JoinColumn(name = "parent_code")
    private Code parent;

    @Builder
    public Code(String code, String memo, Code parent) {
        this.code = code;
        this.memo = memo;
        this.parent = parent;
    }

    public Code update(Code entity) {
        this.code = entity.getCode();
        this.memo = entity.getMemo();
        this.parent = entity.getParent();
        return this;
    }
}

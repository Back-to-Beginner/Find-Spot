package com.backend.domain.post.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum PostType {
    SUCCESS('s', List.of('m')),
    MISSION('m', List.of()),
    COMMENT('c', List.of('m', 's')),
    PROFILE('u', List.of()),
    ;

    final char type; //type code
    final List<Character> parentType; //available parent type
}

package com.backend.domain.image.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum ImageSlice {
    NON(List.of(0, 360, 0, 360)),
    APP(List.of(130, 300, 130, 245)),
    PHA(List.of(130, 230, 120, 300)),
    SNA(List.of(100, 290, 120, 360)),
    SLI(List.of(160, 230, 170, 360)),
    WEL(List.of(130, 260, 0, 160)),
    BOX(List.of(130, 280, 0, 180)),
    SCR(List.of(0, 360, 130, 360)),
    REV(List.of(0, 180, 0, 360)),
    ;
    private final List<Integer> slice;
}

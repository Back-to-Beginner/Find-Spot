package com.backend.domain.image.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum ImageSlice {
    NON(0, List.of(0, 360, 0, 360)),
    APP(2, List.of(130, 300, 130, 245)),
    PHA(13, List.of(130, 230, 120, 300)),
    SNA(29, List.of(100, 290, 120, 360)),
    SLI(-1, List.of(160, 230, 170, 360)),
    WEL(31, List.of(130, 260, 0, 160)),
    BOX(26, List.of(130, 280, 0, 180)),
    SCR(28, List.of(0, 360, 130, 360)),
    REV(27, List.of(0, 180, 0, 360)),
    ;
    private final long id;
    private final List<Integer> slice;
}

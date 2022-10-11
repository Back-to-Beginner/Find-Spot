package com.backend.domain.image.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Path {
//    IMAGE_SAVE_PATH(System.getProperty("user.dir") + "/src/main/resources/images/");
    IMAGE_SAVE_PATH("");


    private final String pathName;

}

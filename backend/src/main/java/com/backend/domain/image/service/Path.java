package com.backend.domain.image.service;

import lombok.Getter;

@Getter
public enum Path {
    imageSavePath(System.getProperty("user.dir") + "/src/main/resources/images/");

    private final String path;
    Path(String path) {
        this.path = path;
    }

}

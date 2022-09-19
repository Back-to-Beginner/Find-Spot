package com.backend.global.domain;

public interface MapFromPathAble<Entity> {
    Entity PathToEntity(Long PostId, String path);
}

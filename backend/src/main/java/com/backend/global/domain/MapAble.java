package com.backend.global.domain;

public interface MapAble <Entity, Request, Response> {
    Entity toEntity(Request request);
    Response fromEntity(Entity entity);
}

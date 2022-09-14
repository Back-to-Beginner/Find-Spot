package com.backend.global.domain;

import java.util.List;

public interface FindByTypeAble<Entity, Response, Param>{
    List<Response> findByType(Param param);
    List<Entity> findEntityByType(Param param);
}

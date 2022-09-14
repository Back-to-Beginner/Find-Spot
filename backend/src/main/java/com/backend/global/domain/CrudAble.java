package com.backend.global.domain;

import java.util.List;

public interface CrudAble<Request, Response> {

    Response save(Request request);

    List<Response> findAll();

    Response findById(long id);

    void deleteById(long id);

    Response update(long id, Request request);
}

package com.backend.global.domain;

import com.backend.global.dto.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface CrudControllerAble<Request> {

//    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse save(Request request);
    ApiResponse findAll();
    ApiResponse findById(Long id);
    ApiResponse delete(Long id);
    ApiResponse update(Long id, Request request);
}

package com.backend.domain.group.controller;

import com.backend.domain.group.dto.GroupRequest;
import com.backend.domain.group.service.GroupService;
import com.backend.global.domain.CrudControllerAble;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/v1/groups") // This means URL's start with /demo (after Application path)
public class GroupController implements
        CrudControllerAble<GroupRequest>
{

    private final GroupService service;

    @Override
    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse save(
            @Validated @RequestBody GroupRequest groupRequest
    ) {
        return created(service.save(groupRequest));
    }

    @Override
    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAll() {
        return ok(service.findAll());
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findById(
            @PathVariable Long id
    ) {
        return ok(service.findById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse delete(
            @PathVariable Long id
    ) {
        service.deleteById(id);
        return noContent();
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public ApiResponse update(
            @PathVariable Long id,
            @Validated @RequestBody GroupRequest groupRequest
    ) {
        return created(service.update(id, groupRequest));
    }

}
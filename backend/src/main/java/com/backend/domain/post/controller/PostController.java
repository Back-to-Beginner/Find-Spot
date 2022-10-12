package com.backend.domain.post.controller;

import com.backend.domain.post.dto.PostRequest;
import com.backend.domain.post.service.PostService;
import com.backend.global.domain.CrudControllerAble;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController implements
        CrudControllerAble<PostRequest> {
    private final PostService service;

    @Override
    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAll() {
        return ok(service.findAll());
    }

    @Override
    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse save(
            @Validated @RequestBody PostRequest request
    ) {
        return created(service.save(request));
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
    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public ApiResponse update(
            @PathVariable Long id,
            @Validated @RequestBody PostRequest request
    ) {
        return created(service.update(id, request));
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

    @GetMapping("/types/{type}")
    @ResponseStatus(OK)
    public ApiResponse findAllByType(
            @PathVariable String type) {
        return ok(service.findByType(type.charAt(0)));
    }

    @GetMapping("/types/{type}/users/{userId}")
    @ResponseStatus(OK)
    public ApiResponse findAllByTypeAndUser(
            @PathVariable String type,
            @PathVariable Long userId
    ) {
        return ok(service.findByTypeAndUser(type.charAt(0), userId));
    }

    @GetMapping("/parent/{parentPostId}/child/{type}")
    @ResponseStatus(OK)
    public ApiResponse findAllChildByParentPostId(
            @PathVariable Long parentPostId,
            @PathVariable String type
    ) {
        return ok(service.findTypeAndParentPost(type.charAt(0), parentPostId));
    }

    @GetMapping("/search/{type}")
    @ResponseStatus(OK)
    public ApiResponse searchTypeAndWord(
            @PathVariable("type") String type,
            @RequestParam("word") String word
    ) {
        return ok(service.search(type.charAt(0), word));
    }


}

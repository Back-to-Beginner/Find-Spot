package com.backend.domain.tag.controller;


import com.backend.domain.moment.dto.MomentMapper;
import com.backend.domain.moment.dto.MomentRequest;
import com.backend.domain.tag.dto.TagRequestDto;
import com.backend.domain.tag.service.TagService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.backend.global.dto.ApiResponse.*;
import static com.backend.global.dto.ApiResponse.ok;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final MomentMapper mapper;

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAll() {
        return ok(tagService.findAll());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse create(@Valid @RequestBody TagRequestDto tagRequestDto) {
        return created(tagService.save(tagRequestDto));
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findOne(@PathVariable Long id) {
        return ok(tagService.findById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public ApiResponse update(@PathVariable Long id, @Validated @RequestBody TagRequestDto tagRequestDto) {
        return created(tagService.updateById(id, tagRequestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ApiResponse delete(@PathVariable Long id) {
        tagService.deleteById(id);
        return noContent();
    }

    @GetMapping("/name")
    @ResponseStatus(OK)
    public ApiResponse findByNameLike(@RequestParam String name){
        return ok(tagService.findByNameLike(name));
    }
}

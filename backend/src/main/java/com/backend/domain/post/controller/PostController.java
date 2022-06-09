package com.backend.domain.post.controller;

import com.backend.domain.post.service.PostService;
import com.backend.global.dto.ApiResponse;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.MappingFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostController {

    private final PostService service;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse getPostByTripId(@PathVariable Long id) {
        return Stream.of(id)
                .map(service::getPostByTripId)
                .map(ApiResponse::ok)
                .findAny()
                .orElseThrow(() -> new MappingFailException(ErrorCode.INVALID_JSON_FORMAT, "데이터 매핑에 실패하였습니다."));
    }
}

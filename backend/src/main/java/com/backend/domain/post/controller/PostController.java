package com.backend.domain.post.controller;

import com.backend.domain.post.domain.entity.PostType;
import com.backend.domain.post.dto.MissionRequest;
import com.backend.domain.post.dto.PostRequest;
import com.backend.domain.post.dto.SuccessRequest;
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

    @PostMapping("/missions")
    @ResponseStatus(CREATED)
    public ApiResponse saveMission(
            @RequestBody @Validated MissionRequest missionRequest
    ) {
        return ok(service.saveMission(missionRequest));
    }

    @GetMapping("/missions")
    @ResponseStatus(OK)
    public ApiResponse findAllMission(
    ) {
        return ok(service.findByType(PostType.MISSION.getType()));
    }

    @PostMapping("/successes")
    @ResponseStatus(CREATED)
    public ApiResponse saveSuccess(
            @RequestBody @Validated SuccessRequest successRequest
    ) {
        return ok(service.saveSuccess(successRequest));
    }

    @GetMapping("/successes")
    @ResponseStatus(OK)
    public ApiResponse findAllSuccess(
    ) {
        return ok(service.findByType(PostType.SUCCESS.getType()));
    }

    @GetMapping("/successes/users/{userId}")
    @ResponseStatus(OK)
    public ApiResponse findAllSuccessByUser(
            @PathVariable Long userId
    ) {
        return ok(service.findByTypeAndUser(PostType.SUCCESS.getType(), userId));
    }

    @GetMapping("/missions/{postId}/successes")
    @ResponseStatus(OK)
    public ApiResponse findAllSuccessByMission(
            @PathVariable Long postId
    ) {
        return ok(service.findByTypeAndParentPost(PostType.SUCCESS.getType(), postId));
    }

}

package com.backend.domain.user.controller;

import com.backend.domain.user.dto.LoginRequest;
import com.backend.domain.user.dto.UserRequest;
import com.backend.domain.user.service.UserService;
import com.backend.global.dto.ApiResponse;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

import static com.backend.global.dto.ApiResponse.created;
import static com.backend.global.dto.ApiResponse.ok;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/v1/users") // This means URL's start with /demo (after Application path)
public class UserController {
    private final UserService service;

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAllUser() {
        return ok(service.findAll());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse createUser(
            @Validated @RequestBody UserRequest userRequest
    ) {
        return created(service.save(userRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findOneUserById(
            @PathVariable Long id
    ) {
        return ok(service.findById(id));
    }

    @PostMapping("/login")
    @ResponseStatus(CREATED)
    public ApiResponse loginUser(
            @Validated @RequestBody LoginRequest request
    ) {
        return Stream.of(request)
                .map(service::login)
                .map(ApiResponse::created)
                .findAny()
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, "User Not Found"));
    }

    @PatchMapping("/{id}/group-join/{groupId}")
    @ResponseStatus(CREATED)
    public ApiResponse addUser(
            @PathVariable Long id,
            @PathVariable Long groupId
    ) {
        return created(service.joinGroup(id, groupId));
    }

    @PatchMapping("/{id}/group-quit")
    @ResponseStatus(CREATED)
    public ApiResponse deleteUser(
            @PathVariable Long id
    ) {
        return created(service.quitGroup(id));
    }
}
package com.backend.domain.user.controller;

import com.backend.domain.user.dto.UserRequest;
import com.backend.domain.user.dto.UserResponse;
import com.backend.domain.user.service.UserService;
import com.backend.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.backend.global.dto.ApiResponse.*;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/v1/users") // This means URL's start with /demo (after Application path)
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(OK)
    public ApiResponse findAllUser() {
        return ok(userService
                .findAllUser()
                .stream()
                .map(UserResponse::of)
                .collect(Collectors.toList())
        );
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiResponse createUser(@Validated @RequestBody UserRequest userRequest) {
        return created(
                UserResponse.of(
                        userService.createUser(
                                userRequest.toEntity()
                        )
                )
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public ApiResponse findOneUserById(@PathVariable Long id) {
        return ok(
                UserResponse.of(
                        userService.findUserById(id)
                )
        );
    }

}
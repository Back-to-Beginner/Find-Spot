package com.backend.domain.user.controller;

import com.backend.domain.user.dto.UserRequest;
import com.backend.domain.user.dto.UserResponse;
import com.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/v1/users") // This means URL's start with /demo (after Application path)
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUser() {
        return ResponseEntity.ok(userService
                .findAllUser()
                .stream()
                .map(UserResponse::of)
                .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Validated @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(UserResponse
                .of(userService
                        .createUser(userRequest.toEntity())
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findOneUserById(@PathVariable Long id) {
        return ResponseEntity.ok(UserResponse
                .of(userService
                        .findUserById(id)
                )
        );
    }
}
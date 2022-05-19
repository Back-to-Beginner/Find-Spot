package com.backend.global.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@Data
@Builder
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public static <T> ApiResponse ok(T data) {
        return ApiResponse.builder()
                .code(OK.name())
                .message(OK.getReasonPhrase())
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse created(T data) {
        return ApiResponse.builder()
                .code(CREATED.name())
                .message(CREATED.getReasonPhrase())
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiResponse noContent() {
        return ApiResponse.builder()
                .code(NOT_FOUND.name())
                .message("Delete Complete")
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse notFound() {
        return ApiResponse.builder()
                .code(NOT_FOUND.name())
                .message(NOT_FOUND.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse serverError() {
        return ApiResponse.builder()
                .code(INTERNAL_SERVER_ERROR.name())
                .message(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse notImplemented() {
        return ApiResponse.builder()
                .code(NOT_IMPLEMENTED.name())
                .message(NOT_IMPLEMENTED.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();
    }
}

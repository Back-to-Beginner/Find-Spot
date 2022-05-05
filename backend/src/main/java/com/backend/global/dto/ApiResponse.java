package com.backend.global.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@Data
@Builder
public class ApiResponse<T> {
    private final boolean success = true;
    private String code;
    private String message;
    private T data;
    private LocalDateTime timestamp;


    public static <T> ApiResponse success(T data) {
        return ApiResponse.builder()
                .code("OK")
                .message(OK.getReasonPhrase())
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse created(T data) {
        return ApiResponse.builder()
                .code("CREATE")
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

    public static <T> ApiResponse notFound(T data) {
        return ApiResponse.builder()
                .code(NOT_FOUND.name())
                .message(NOT_FOUND.getReasonPhrase())
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse serverError(T data) {
        return ApiResponse.builder()
                .code(INTERNAL_SERVER_ERROR.name())
                .message(INTERNAL_SERVER_ERROR.getReasonPhrase())
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

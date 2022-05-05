package com.backend.global.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {

    private final ErrorCode Code;

    public NotFoundException(ErrorCode errorCode, String message) {
        super(message);
        this.Code = errorCode;
    }

}

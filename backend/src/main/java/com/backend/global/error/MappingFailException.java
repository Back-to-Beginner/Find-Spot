package com.backend.global.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MappingFailException extends RuntimeException{

    private final ErrorCode Code;

    public MappingFailException(ErrorCode errorCode, String message) {
        super(message);
        this.Code = errorCode;
    }
}

package com.hangongsu.matching.global.exception;

import com.hangongsu.matching.global.exception.code.ErrorCode;

public class HangongsuException extends RuntimeException {
    private final ErrorCode errorCode;

    public HangongsuException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

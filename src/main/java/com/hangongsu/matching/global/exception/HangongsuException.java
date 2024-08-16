package com.hangongsu.matching.global.exception;

public class HangongsuException extends RuntimeException {
    private final ErrorCode errorCode;

    public HangongsuException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

package com.hangongsu.support.exception;

public class BaseException extends RuntimeException {
    private final String loggingMessage;
    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.loggingMessage = errorCode.getMessage();
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode, String loggingMessage) {
        super(errorCode.getMessage());
        this.loggingMessage = loggingMessage;
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

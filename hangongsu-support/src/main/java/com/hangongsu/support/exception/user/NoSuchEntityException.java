package com.hangongsu.support.exception.user;

import com.hangongsu.support.exception.BaseException;
import com.hangongsu.support.exception.ErrorCode;

public class NoSuchEntityException extends BaseException {
    public NoSuchEntityException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NoSuchEntityException(ErrorCode errorCode, String loggingMessage) {
        super(errorCode, loggingMessage);
    }
}

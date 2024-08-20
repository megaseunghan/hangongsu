package com.hangongsu.support.exception.auth;

import com.hangongsu.support.exception.BaseException;
import com.hangongsu.support.exception.ErrorCode;

public class NotSupportOAuthProviderException extends BaseException {

    public NotSupportOAuthProviderException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NotSupportOAuthProviderException(ErrorCode errorCode, String loggingMessage) {
        super(errorCode, loggingMessage);
    }
}

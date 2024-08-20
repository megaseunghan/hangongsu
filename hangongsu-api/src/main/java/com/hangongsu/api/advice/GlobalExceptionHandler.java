package com.hangongsu.api.advice;

import com.hangongsu.api.dto.response.ErrorResponse;
import com.hangongsu.support.exception.ErrorCode;
import com.hangongsu.support.exception.auth.NotSupportOAuthProviderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String LOG_FORMAT = "Class :: {} | Code :: {} | Message :: {}";

    @ExceptionHandler(NotSupportOAuthProviderException.class)
    public ResponseEntity<ErrorResponse> handleNotSupportOAuthProviderException(NotSupportOAuthProviderException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.of(errorCode);

        log.error(LOG_FORMAT, e.getClass().getSimpleName(), errorCode.getCode(), errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse response = ErrorResponse.of(errorCode);

        log.error(LOG_FORMAT, e.getClass().getSimpleName(), errorCode.getCode(), errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }
}

package com.hangongsu.matching.global.exception.dto.response;

import com.hangongsu.matching.global.exception.code.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private List<FieldError> errors;
    private String code;

    private ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(ErrorCode errorCode, BindingResult bindingResult) {
        this.message = errorCode.getMessage();
        this.errors = FieldError.of(bindingResult);
        this.code = errorCode.getCode();
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
        return new ErrorResponse(errorCode, bindingResult);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()
                    )).toList();
        }
    }
}

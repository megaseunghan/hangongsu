package com.hangongsu.support.exception;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "SV_001", "예상치 못한 서버 에러가 발생하였습니다.")

    ;
    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

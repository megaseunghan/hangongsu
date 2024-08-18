package com.hangongsu.core.domain.user.vo;

public enum Platform {
    KAKAO;

    public static Platform from(String platform) {
        // TODO("예외 처리 추가 240817")
        return Platform.valueOf(platform.toUpperCase());
    }
}

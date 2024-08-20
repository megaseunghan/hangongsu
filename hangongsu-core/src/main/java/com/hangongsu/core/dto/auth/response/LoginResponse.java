package com.hangongsu.core.dto.auth.response;

public record LoginResponse(
        long id,
        String accessToken
) {
    public static LoginResponse from(long id, TokenResponse tokenResponse) {
        return new LoginResponse(
                id,
                tokenResponse.accessToken()
        );
    }
}

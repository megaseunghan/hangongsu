package com.hangongsu.core.port.auth.out;

import com.hangongsu.core.dto.auth.response.TokenResponse;

public interface TokenClient {
    TokenResponse createAccessToken(Long userId);
}

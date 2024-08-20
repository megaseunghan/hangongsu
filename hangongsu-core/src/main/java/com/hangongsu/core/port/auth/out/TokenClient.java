package com.hangongsu.core.port.auth.out;

import com.hangongsu.core.dto.auth.response.TokenResponse;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;

public interface TokenClient {
    TokenResponse createAccessToken(UserInfoResponse userInfo);
}

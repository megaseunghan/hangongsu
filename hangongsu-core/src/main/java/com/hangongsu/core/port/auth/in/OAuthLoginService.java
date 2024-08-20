package com.hangongsu.core.port.auth.in;

import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.LoginResponse;

public interface OAuthLoginService {
    LoginResponse login(String authorizationCode, Platform platform);
}

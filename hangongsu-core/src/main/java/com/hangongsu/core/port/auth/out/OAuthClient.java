package com.hangongsu.core.port.auth.out;

import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;

public interface OAuthClient {

    Platform getPlatform();

    String getAccessToken(String authorizationCode);

    UserInfoResponse retrieveUserInfo(String accessToken);
}

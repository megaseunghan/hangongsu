package com.hangongsu.core.application.auth;

import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.LoginResponse;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;
import com.hangongsu.core.port.auth.in.OAuthLoginService;
import com.hangongsu.core.port.user.in.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginServiceImpl implements OAuthLoginService {

    private final OAuthClientComposite oAuthClient;
    private final UserService userService;

    @Override
    public LoginResponse login(String authorizationCode, Platform platform) {
        String accessToken = oAuthClient.getAccessToken(authorizationCode, platform);
        UserInfoResponse userInfo = oAuthClient.retrieveUserInfo(accessToken, platform);

        return userService.registerIfAbsent(userInfo);
    }
}

package com.hangongsu.core.application.auth;

import com.hangongsu.core.domain.user.entity.User;
import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.LoginResponse;
import com.hangongsu.core.dto.auth.response.TokenResponse;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;
import com.hangongsu.core.port.auth.in.OAuthLoginService;
import com.hangongsu.core.port.auth.out.TokenClient;
import com.hangongsu.core.port.user.in.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginServiceImpl implements OAuthLoginService {

    private final OAuthClientComposite oAuthClient;
    private final UserService userService;
    private final TokenClient tokenClient;

    @Override
    public LoginResponse login(String authorizationCode, Platform platform) {
        String accessToken = oAuthClient.getAccessToken(authorizationCode, platform);
        UserInfoResponse userInfo = oAuthClient.retrieveUserInfo(accessToken, platform);
        TokenResponse token = tokenClient.createAccessToken(userInfo);
        User user = userService.registerIfAbsent(userInfo);

        return LoginResponse.from(user.getUserId(), token);
    }
}

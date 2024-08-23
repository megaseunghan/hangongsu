package com.hangongsu.core.application.auth;

import com.hangongsu.core.domain.user.entity.User;
import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.LoginResponse;
import com.hangongsu.core.dto.auth.response.TokenResponse;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;
import com.hangongsu.core.port.auth.in.OAuthLoginService;
import com.hangongsu.core.port.auth.out.TokenClient;
import com.hangongsu.core.port.user.in.UserLoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OAuthLoginServiceImpl.class})
class OAuthLoginServiceTest {

    @Autowired
    private OAuthLoginService oAuthLoginService;
    @MockBean
    private OAuthClientComposite oAuthClient;
    @MockBean
    private UserLoginService userLoginService;
    @MockBean
    private TokenClient tokenClient;

    @Test
    void 엑세스토큰과_유저정보를_획득했을때_로그인을_진행할_수_있다() throws Exception {
        // given
        String authorizationCode = "auth";
        Platform platform = Platform.KAKAO;
        String accessToken = "accessToken";

        UserInfoResponse userInfo = new UserInfoResponse(
                Platform.KAKAO,
                "hangongsu@test.com",
                "hangongsu",
                "hangongsu",
                1998L
        );

        TokenResponse jwtAccessToken = new TokenResponse("jwtAccessToken");

        User user = new User(
                1L,
                Platform.KAKAO,
                "hangongsu@test.com",
                "hangongsu",
                "hangongsu",
                1998L
        );

        given(oAuthClient.getAccessToken(authorizationCode, platform)).willReturn(accessToken);
        given(oAuthClient.retrieveUserInfo(accessToken, platform)).willReturn(userInfo);
        given(tokenClient.createAccessToken(userInfo)).willReturn(jwtAccessToken);
        given(userLoginService.login(userInfo)).willReturn(user);

        // when
        LoginResponse result = oAuthLoginService.login(authorizationCode, platform);

        // then
        assertThat(result).isEqualTo(LoginResponse.from(user.getUserId(), jwtAccessToken));
    }

}
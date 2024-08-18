package com.hangongsu.infratstructure.client.auth.kakao;

import com.hangongsu.infratstructure.client.auth.OAuthClient;
import com.hangongsu.support.property.auth.KakaoOAuthProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOAuthClient implements OAuthClient {

    private final KakaoOAuthProperty kakaoOAuthProperty;

    @Override
    public String getAccessToken(String authorizationCode) {
        return null;
    }

    @Override
    public String retrieveUserInfo(String accessToken) {
        return null;
    }
}

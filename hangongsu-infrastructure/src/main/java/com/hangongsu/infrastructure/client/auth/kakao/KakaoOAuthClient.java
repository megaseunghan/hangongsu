package com.hangongsu.infrastructure.client.auth.kakao;

import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;
import com.hangongsu.core.port.auth.out.OAuthClient;
import com.hangongsu.infrastructure.dto.auth.kakao.KakaoAuthResponse;
import com.hangongsu.infrastructure.dto.auth.kakao.KakaoUserInfoResponse;
import com.hangongsu.support.property.auth.KakaoOAuthProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class KakaoOAuthClient implements OAuthClient {

    private final KakaoOAuthProperty kakaoOAuthProperty;
    private final RestClient restClient;
    @Override
    public Platform getPlatform() {
        return Platform.KAKAO;
    }

    @Override
    public String getAccessToken(String authorizationCode) {
        URI requestUri = URI.create(kakaoOAuthProperty.getAuthBaseUri() + "/oauth/token");

        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();

        requestData.add("grant_type", "authorization_code");
        requestData.add("client_id", kakaoOAuthProperty.getClientId());
        requestData.add("client_secret", kakaoOAuthProperty.getClientSecret());
        requestData.add("code", authorizationCode);

        KakaoAuthResponse authResponse = Objects.requireNonNull(restClient.post()
                .uri(requestUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(requestData)
                .retrieve()
                .body(KakaoAuthResponse.class));

        return authResponse.accessToken();
    }

    @Override
    public UserInfoResponse retrieveUserInfo(String accessToken) {
        URI requestUri = URI.create(kakaoOAuthProperty.getApiBaseUri() + "/v2/user/me");

        KakaoUserInfoResponse kakaoUserInfoResponse = Objects.requireNonNull(restClient.post()
                .uri(requestUri)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .body(KakaoUserInfoResponse.class));

        return kakaoUserInfoResponse.toDomain();
    }
}

package com.hangongsu.infratstructure.client.auth;

public interface OAuthClient {

    String getAccessToken(String authorizationCode);

    String retrieveUserInfo(String accessToken);
}

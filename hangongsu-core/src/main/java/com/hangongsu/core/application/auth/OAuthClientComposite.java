package com.hangongsu.core.application.auth;

import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;
import com.hangongsu.core.port.auth.out.OAuthClient;
import com.hangongsu.support.exception.ErrorCode;
import com.hangongsu.support.exception.auth.NotSupportOAuthProviderException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OAuthClientComposite {

    private final Map<Platform, OAuthClient> clientMap;

    public OAuthClientComposite(Set<OAuthClient> clients) {
        this.clientMap = clients.stream()
                .collect(Collectors.toMap(
                        OAuthClient::getPlatform,
                        Function.identity())
                );
    }

    public String getAccessToken(String authorizationCode, Platform platform) {
        return getClient(platform).getAccessToken(authorizationCode);
    }

    public UserInfoResponse retrieveUserInfo(String accessToken, Platform platform) {
        return getClient(platform).retrieveUserInfo(accessToken);
    }

    private OAuthClient getClient(Platform platform) {
        return Optional.ofNullable(clientMap.get(platform))
                .orElseThrow(() -> new NotSupportOAuthProviderException(ErrorCode.NOT_SUPPORT_OAUTH));
    }

}

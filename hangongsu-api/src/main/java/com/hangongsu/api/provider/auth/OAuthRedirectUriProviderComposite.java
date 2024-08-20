package com.hangongsu.api.provider.auth;

import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.support.exception.ErrorCode;
import com.hangongsu.support.exception.auth.NotSupportOAuthProviderException;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OAuthRedirectUriProviderComposite {

    private final Map<Platform, OAuthRedirectUriProvider> providerMap;

    public OAuthRedirectUriProviderComposite(Set<OAuthRedirectUriProvider> providers) {
        this.providerMap = providers.stream()
                .collect(Collectors.toMap(
                        OAuthRedirectUriProvider::getPlatform,
                        Function.identity())
                );
    }

    public URI generateRedirectUri(Platform platform) {
        return getProvider(platform).generateRedirectUri();
    }

    private OAuthRedirectUriProvider getProvider(Platform platform) {
        return Optional.ofNullable(providerMap.get(platform))
                .orElseThrow(() -> new NotSupportOAuthProviderException(ErrorCode.NOT_SUPPORT_OAUTH));
    }
}

package com.hangongsu.support.property.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperty {
    private final String issuer;
    private final String secretKey;
    private final String accessTokenExpiredDeadLine;
}

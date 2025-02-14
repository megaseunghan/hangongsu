package com.hangongsu.infrastructure.client.auth.jwt;

import com.hangongsu.core.dto.auth.response.TokenResponse;
import com.hangongsu.core.port.auth.out.TokenClient;
import com.hangongsu.support.property.jwt.JwtProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenClient implements TokenClient, InitializingBean {

    private final JwtProperty jwtProperty;

    private Key key;

    @Override
    public void afterPropertiesSet() {
        this.key = Keys.hmacShaKeyFor(jwtProperty.getSecretKey().getBytes());
    }

    @Override
    public TokenResponse createAccessToken(Long userId) {
        Date expiredDate = parseDate(jwtProperty.getAccessTokenExpiredDeadLine());

        Claims claims = Jwts.claims().add("userId", userId).build();

        String accessToken = generateAccessToken(claims, expiredDate);
        // TODO: refreshToken
        return new TokenResponse(accessToken);
    }

    private String generateAccessToken(Claims claims, Date expiredDate) {
        return Jwts.builder()
                .issuer(jwtProperty.getIssuer())
                .claims(claims)
                .expiration(expiredDate)
                .signWith(key)
                .compact();
    }

    private Date parseDate(String accessTokenExpiredDeadLine) {
        long expiredTimeMills = 1000 * 60 * Long.parseLong(accessTokenExpiredDeadLine);
        return new Date(System.currentTimeMillis() + expiredTimeMills);
    }
}

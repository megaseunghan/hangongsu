package com.hangongsu.support.common.config;

import com.hangongsu.support.property.auth.KakaoOAuthProperty;
import com.hangongsu.support.property.jwt.JwtProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {KakaoOAuthProperty.class, JwtProperty.class})
public class EnablePropertiesConfig {
}

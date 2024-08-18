package com.hangongsu.support.common.config;

import com.hangongsu.support.property.auth.KakaoOAuthProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.hangongsu.support", lazyInit = true)
@EnableConfigurationProperties({KakaoOAuthProperty.class})
public class EnablePropertiesConfig {
}

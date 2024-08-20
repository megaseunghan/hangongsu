package com.hangongsu.infrastructure.common;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.hangongsu.infrastructure")
@EntityScan(basePackages = "com.hangongsu.core")
public class JpaConfig {
}

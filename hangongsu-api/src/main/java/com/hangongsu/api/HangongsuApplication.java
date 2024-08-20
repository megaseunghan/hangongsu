package com.hangongsu.api;

import com.hangongsu.core.common.ServiceConfig;
import com.hangongsu.infrastructure.common.InfraStructureConfig;
import com.hangongsu.support.common.config.SupportConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({SupportConfig.class, ServiceConfig.class, InfraStructureConfig.class})
@SpringBootApplication
public class HangongsuApplication {

    public static void main(String[] args) {
        SpringApplication.run(HangongsuApplication.class, args);
    }

}

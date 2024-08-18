package com.hangongsu.api;

import com.hangongsu.support.common.config.EnablePropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(EnablePropertiesConfig.class)
@SpringBootApplication
public class HangongsuApplication {

    public static void main(String[] args) {
        SpringApplication.run(HangongsuApplication.class, args);
    }

}

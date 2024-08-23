package com.hangongsu.api.common.config;

import com.hangongsu.core.common.CoreConfig;
import com.hangongsu.infrastructure.common.InfraStructureConfig;
import com.hangongsu.support.common.config.SupportConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SupportConfig.class, CoreConfig.class, InfraStructureConfig.class})
public class ModuleImportConfig {
}

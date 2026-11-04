package top.continew.admin.system.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 系统管理配置
 *
 * @author bixiu
 * @since 4.0.0
 */
@Configuration
public class SystemConfiguration {

    /**
     * API 文档分组配置
     */
    @Bean
    public GroupedOpenApi systemModuleApi() {
        return GroupedOpenApi.builder().group("system").displayName("系统管理")
            .pathsToMatch("/system/**").build();
    }
}

package top.continew.admin.auth.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 系统认证配置
 *
 * @author Charles7c
 * @since 4.0.0
 */
@Configuration
public class AuthConfiguration {

    /**
     * API 文档分组配置
     */
    @Bean
    public GroupedOpenApi authModuleApi() {
        return GroupedOpenApi.builder()
            .group("auth")
            .displayName("系统认证")
            .pathsToMatch("/auth/**", "/monitor/online/**")
            .build();
    }
}

package top.continew.admin.common.config.doc;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局接口文档配置
 *
 * @author bixiu
 * @since 4.0.0
 */
@Configuration
public class GlobalSpringDocConfiguration {

    @Bean
    public GroupedOpenApi allModuleApi() {
        return GroupedOpenApi.builder()
            .group("all")
            .displayName("全部接口")
            .pathsToMatch("/**")
            .packagesToExclude("/error")
            .build();
    }

    @Bean
    public GroupedOpenApi commonModuleApi() {
        return GroupedOpenApi.builder()
            .group("common")
            .displayName("通用接口")
            .pathsToMatch("/captcha/**", "/dashboard/**")
            .build();
    }
}

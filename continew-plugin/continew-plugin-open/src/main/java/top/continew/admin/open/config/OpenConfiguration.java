package top.continew.admin.open.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 能力开放配置
 *
 * @author bixiu
 * @since 4.0.0
 */
@Configuration
public class OpenConfiguration {

    /**
     * API 文档分组配置
     */
    @Bean
    public GroupedOpenApi openModuleApi() {
        return GroupedOpenApi.builder().group("open").displayName("能力开放").pathsToMatch("/open/**")
            .build();
    }
}

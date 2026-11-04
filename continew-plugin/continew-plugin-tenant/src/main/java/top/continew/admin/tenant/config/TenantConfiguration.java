package top.continew.admin.tenant.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.continew.admin.common.config.TenantExtensionProperties;
import top.continew.admin.tenant.service.TenantService;
import top.continew.starter.extension.tenant.annotation.ConditionalOnEnabledTenant;
import top.continew.starter.extension.tenant.config.TenantProvider;

/**
 * 租户配置
 *
 * @author bixiu
 * @since 4.0.0
 */
@Configuration
public class TenantConfiguration {

    /**
     * 租户扩展配置属性
     */
    @Bean
    public TenantExtensionProperties tenantExtensionProperties() {
        return new TenantExtensionProperties();
    }

    /**
     * 租户提供者
     */
    @Bean
    @ConditionalOnEnabledTenant
    public TenantProvider tenantProvider(TenantExtensionProperties tenantExtensionProperties,
        TenantService tenantService) {
        return new DefaultTenantProvider(tenantExtensionProperties, tenantService);
    }

    /**
     * API 文档分组配置
     */
    @Bean
    public GroupedOpenApi tenantModuleApi() {
        return GroupedOpenApi.builder().group("tenant").displayName("租户管理")
            .pathsToMatch("/tenant/**").build();
    }
}

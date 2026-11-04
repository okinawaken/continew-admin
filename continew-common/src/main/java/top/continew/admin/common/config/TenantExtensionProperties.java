package top.continew.admin.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import top.continew.starter.core.constant.PropertiesConstants;
import top.continew.starter.extension.tenant.context.TenantContextHolder;

import java.util.List;

/**
 * 租户扩展配置属性
 *
 * @author 小熊
 * @author Charles7c
 * @since 4.0.0
 */
@Data
@ConfigurationProperties(prefix = PropertiesConstants.TENANT)
public class TenantExtensionProperties {

    /**
     * 请求头中租户编码键名（默认：X-Tenant-Code）
     */
    private String tenantCodeHeader = "X-Tenant-Code";

    /**
     * 默认租户 ID（默认：0）
     */
    private Long defaultTenantId = 0L;

    /**
     * 忽略菜单 ID（租户不能使用的菜单）
     */
    private List<Long> ignoreMenus;

    /**
     * 是否为默认租户
     *
     * @return 是否为默认租户
     */
    public boolean isDefaultTenant() {
        return defaultTenantId != null && defaultTenantId.equals(TenantContextHolder.getTenantId());
    }
}

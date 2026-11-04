package top.continew.admin.tenant.constant;

import top.continew.admin.common.constant.CacheConstants;

/**
 * 租户缓存相关常量
 *
 * @author bixiu
 * @since 4.0.0
 */
public class TenantCacheConstants {

    /**
     * 分隔符
     */
    public static final String DELIMITER = CacheConstants.DELIMITER;

    /**
     * 租户前缀
     */
    public static final String TENANT_KEY_PREFIX = "TENANT" + DELIMITER;

    private TenantCacheConstants() {
    }
}

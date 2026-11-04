package top.continew.admin.common.api.tenant;

import top.continew.admin.common.model.dto.TenantDTO;

/**
 * 租户数据 API
 *
 * @author bixiu
 * @author bixiu
 * @since 3.4.1
 */
public interface TenantDataApi {

    /**
     * 初始化数据
     *
     * @param tenant 租户信息
     */
    void init(TenantDTO tenant);

    /**
     * 使当前租户的认证会话失效。
     */
    void invalidateSessions();

    /**
     * 清除数据
     */
    void clear();
}

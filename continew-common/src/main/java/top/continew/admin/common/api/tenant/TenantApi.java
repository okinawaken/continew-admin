package top.continew.admin.common.api.tenant;

import java.util.List;

/**
 * 租户业务 API
 *
 * @author Charles7c
 * @since 4.0.0
 */
public interface TenantApi {

    /**
     * 校验租户是否可以继续提供服务。
     *
     * @param tenantId 租户 ID
     */
    void checkStatus(Long tenantId);

    /**
     * 绑定租户管理员用户
     *
     * @param tenantId 租户 ID
     * @param userId   用户 ID
     */
    void bindAdminUser(Long tenantId, Long userId);

    /**
     * 查询指定套餐当前关联的租户 ID。
     *
     * @param packageId 套餐 ID
     * @return 租户 ID 列表
     */
    List<Long> listIdByPackageId(Long packageId);
}

package top.continew.admin.tenant.service;

import top.continew.admin.common.base.service.BaseService;
import top.continew.admin.tenant.model.entity.TenantDO;
import top.continew.admin.tenant.model.query.TenantQuery;
import top.continew.admin.tenant.model.req.TenantReq;
import top.continew.admin.tenant.model.resp.TenantDetailResp;
import top.continew.admin.tenant.model.resp.TenantResp;
import top.continew.starter.data.service.IService;

import java.util.List;

/**
 * 租户业务接口
 *
 * @author bixiu
 * @author bixiu
 * @since 3.4.1
 */
public interface TenantService
    extends BaseService<TenantResp, TenantDetailResp, TenantQuery, TenantReq>, IService<TenantDO> {

    /**
     * 根据域名查询
     *
     * @param domain 域名
     * @return ID
     */
    Long getIdByDomain(String domain);

    /**
     * 根据编码查询
     *
     * @param code 编码
     * @return ID
     */
    Long getIdByCode(String code);

    /**
     * 检查租户状态
     *
     * @param id ID
     */
    void checkStatus(Long id);

    /**
     * 更新租户菜单
     *
     * @param newMenuIds 新菜单 ID 列表
     * @param packageId  套餐 ID
     */
    void updateTenantMenu(List<Long> newMenuIds, Long packageId);

    /**
     * 使指定套餐下全部租户的认证会话失效。
     *
     * @param packageId 套餐 ID
     */
    void invalidateSessionsByPackageId(Long packageId);

    /**
     * 根据套餐 ID 查询数量
     *
     * @param packageIds 套餐 ID 列表
     * @return 租户数量
     */
    Long countByPackageIds(List<Long> packageIds);
}

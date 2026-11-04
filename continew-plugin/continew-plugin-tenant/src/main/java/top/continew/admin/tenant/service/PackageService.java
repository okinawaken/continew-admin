package top.continew.admin.tenant.service;

import top.continew.admin.common.base.service.BaseService;
import top.continew.admin.tenant.model.entity.PackageDO;
import top.continew.admin.tenant.model.query.PackageQuery;
import top.continew.admin.tenant.model.req.PackageReq;
import top.continew.admin.tenant.model.resp.PackageDetailResp;
import top.continew.admin.tenant.model.resp.PackageResp;
import top.continew.starter.data.service.IService;

/**
 * 套餐业务接口
 *
 * @author bixiu
 * @since 3.4.1
 */
public interface PackageService extends
    BaseService<PackageResp, PackageDetailResp, PackageQuery, PackageReq>, IService<PackageDO> {

    /**
     * 检查套餐状态
     *
     * @param id ID
     */
    void checkStatus(Long id);
}

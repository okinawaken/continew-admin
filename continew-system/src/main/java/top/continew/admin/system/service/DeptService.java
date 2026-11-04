package top.continew.admin.system.service;

import top.continew.admin.common.base.service.BaseService;
import top.continew.admin.system.model.entity.DeptDO;
import top.continew.admin.system.model.query.DeptQuery;
import top.continew.admin.system.model.req.DeptReq;
import top.continew.admin.system.model.resp.DeptResp;
import top.continew.starter.data.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 部门业务接口
 *
 * @author bixiu
 * @since 1.0.0
 */
public interface DeptService
    extends BaseService<DeptResp, DeptResp, DeptQuery, DeptReq>, IService<DeptDO> {

    /**
     * 查询子部门列表
     *
     * @param id ID
     * @return 子部门列表
     */
    List<DeptDO> listChildren(Long id);

    /**
     * 通过名称查询部门
     *
     * @param list 名称列表
     * @return 部门列表
     */
    List<DeptDO> listByNames(List<String> list);

    /**
     * 通过名称查询部门数量
     *
     * @param deptNames 名称列表
     * @return 部门数量
     */
    int countByNames(Set<String> deptNames);
}

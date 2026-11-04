package top.continew.admin.system.service;

import top.continew.admin.common.base.service.BaseService;
import top.continew.admin.system.model.entity.MenuDO;
import top.continew.admin.system.model.query.MenuQuery;
import top.continew.admin.system.model.req.MenuReq;
import top.continew.admin.system.model.resp.MenuResp;
import top.continew.starter.data.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 菜单业务接口
 *
 * @author Charles7c
 * @since 1.0.0
 */
public interface MenuService
    extends BaseService<MenuResp, MenuResp, MenuQuery, MenuReq>, IService<MenuDO> {

    /**
     * 根据用户 ID 查询
     *
     * @param userId 用户 ID
     * @return 权限码集合
     */
    Set<String> listPermissionByUserId(Long userId);

    /**
     * 根据角色 ID 查询
     *
     * @param roleId 角色 ID
     * @return 菜单列表
     */
    List<MenuResp> listByRoleId(Long roleId);

    /**
     * 查询租户排除的菜单 ID 列表
     *
     * @return 租户排除的菜单 ID 列表
     */
    List<Long> listExcludeTenantMenu();
}

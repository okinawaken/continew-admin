package top.continew.admin.system.mapper;

import top.continew.admin.system.model.entity.RoleMenuDO;
import top.continew.starter.data.mapper.BaseMapper;

import java.util.List;

/**
 * 角色和菜单 Mapper
 *
 * @author Charles7c
 * @since 1.0.0
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenuDO> {

    /**
     * 根据角色 ID 列表查询
     *
     * @param roleIds 角色 ID 列表
     * @return 菜单 ID 列表
     */
    List<Long> selectMenuIdByRoleIds(List<Long> roleIds);
}

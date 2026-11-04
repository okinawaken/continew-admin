package top.continew.admin.common.api.system;

/**
 * 角色业务 API
 *
 * @author bixiu
 * @since 4.0.0
 */
public interface RoleApi {

    /**
     * 根据编码查询 ID
     *
     * @param code 编码
     * @return 角色 ID
     */
    Long getIdByCode(String code);

    /**
     * 更新用户上下文
     *
     * @param roleId 角色 ID
     */
    void updateUserContext(Long roleId);
}

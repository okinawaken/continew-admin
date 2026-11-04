package top.continew.admin.system.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.common.api.system.RoleApi;
import top.continew.admin.system.service.RoleService;

/**
 * 角色业务 API 实现
 *
 * @author Charles7c
 * @since 4.0.0
 */
@Service
@RequiredArgsConstructor
public class RoleApiImpl implements RoleApi {

    private final RoleService baseService;

    @Override
    public Long getIdByCode(String code) {
        return baseService.getIdByCode(code);
    }

    @Override
    public void updateUserContext(Long roleId) {
        baseService.updateUserContext(roleId);
    }
}

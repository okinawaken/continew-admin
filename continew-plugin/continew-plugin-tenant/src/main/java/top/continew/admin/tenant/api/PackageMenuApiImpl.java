package top.continew.admin.tenant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.common.api.tenant.PackageMenuApi;
import top.continew.admin.tenant.service.PackageMenuService;

import java.util.List;

/**
 * 套餐和菜单关联业务 API 实现
 *
 * @author bixiu
 * @since 4.0.0
 */
@Service
@RequiredArgsConstructor
public class PackageMenuApiImpl implements PackageMenuApi {

    private final PackageMenuService baseService;

    @Override
    public List<Long> listMenuIdsByPackageId(Long packageId) {
        return baseService.listMenuIdsByPackageId(packageId);
    }
}

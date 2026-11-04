package top.continew.admin.tenant.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.common.api.tenant.TenantApi;
import top.continew.starter.auth.refresh.token.api.AuthPolicyLockTarget;
import top.continew.starter.auth.refresh.token.api.AuthPolicyLockTargetResolver;

import java.util.Collection;

/**
 * 将套餐变更影响的租户解析为认证会话策略锁目标
 *
 * @author luoqiz
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class PackageTenantPolicyLockTargetResolver implements AuthPolicyLockTargetResolver {

    private final TenantApi tenantApi;

    @Override
    public Collection<AuthPolicyLockTarget> resolve(Object[] args) {
        if (args.length <= 1 || !(args[1] instanceof Long packageId)) {
            throw new IllegalArgumentException("认证套餐策略锁参数无效");
        }
        return tenantApi.listIdByPackageId(packageId).stream().map(AuthPolicyLockTarget::tenant)
            .toList();
    }
}

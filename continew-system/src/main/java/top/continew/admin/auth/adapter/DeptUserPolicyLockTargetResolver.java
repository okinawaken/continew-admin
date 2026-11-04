package top.continew.admin.auth.adapter;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.system.mapper.user.UserMapper;
import top.continew.admin.system.model.entity.user.UserDO;
import top.continew.starter.auth.refresh.token.api.AuthPolicyLockTarget;
import top.continew.starter.auth.refresh.token.api.AuthPolicyLockTargetResolver;

import java.util.Collection;

/**
 * 将部门变更影响的直属用户解析为认证会话策略锁目标
 *
 * @author bixiu
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class DeptUserPolicyLockTargetResolver implements AuthPolicyLockTargetResolver {

    private final UserMapper userMapper;

    @Override
    public Collection<AuthPolicyLockTarget> resolve(Object[] args) {
        if (args.length <= 1 || !(args[1] instanceof Long deptId)) {
            throw new IllegalArgumentException("认证部门策略锁参数无效");
        }
        return userMapper.selectList(Wrappers.<UserDO>lambdaQuery().select(UserDO::getId)
            .eq(UserDO::getDeptId, deptId)).stream().map(UserDO::getId)
            .map(AuthPolicyLockTarget::user).toList();
    }
}

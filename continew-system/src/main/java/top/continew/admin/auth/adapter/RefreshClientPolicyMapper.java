package top.continew.admin.auth.adapter;

import top.continew.admin.system.enums.LogoutModeEnum;
import top.continew.admin.system.enums.ReplacedRangeEnum;
import top.continew.admin.system.model.resp.ClientResp;
import top.continew.starter.auth.refresh.token.enums.LogoutReasonEnum;
import top.continew.starter.auth.refresh.token.enums.SessionReplacementScope;
import top.continew.starter.auth.refresh.token.model.RefreshClientPolicy;

/**
 * 系统客户端配置到认证会话策略的边界转换器。
 *
 * @author bixiu
 */
public final class RefreshClientPolicyMapper {

    private RefreshClientPolicyMapper() {
    }

    /**
     * 将系统客户端响应转换为认证会话模块的最小策略模型。
     *
     * @param client 系统客户端配置
     * @return 认证会话客户端策略
     */
    public static RefreshClientPolicy from(ClientResp client) {
        SessionReplacementScope replacementScope = client.getReplacedRange() == null ? null
            : ReplacedRangeEnum.ALL_DEVICE_TYPE.equals(client.getReplacedRange())
                ? SessionReplacementScope.ALL_CLIENT_TYPES
                : SessionReplacementScope.CURRENT_CLIENT_TYPE;
        return new RefreshClientPolicy(client.getClientId(), client.getClientType(),
            client.getRefreshTokenTimeout(), client.getRefreshTokenMode(),
            Boolean.TRUE.equals(client.getIsConcurrent()), replacementScope,
            client.getMaxLoginCount(), toLogoutReason(client.getOverflowLogoutMode()));
    }

    /**
     * 客户端配置的注销模式决定登录数量超限时被淘汰会话看到的提示
     *
     * @param overflowLogoutMode 注销模式
     * @return 认证会话模块的注销原因
     */
    private static LogoutReasonEnum toLogoutReason(LogoutModeEnum overflowLogoutMode) {
        if (overflowLogoutMode == null) {
            return LogoutReasonEnum.REPLACED;
        }
        try {
            return LogoutReasonEnum.valueOf(overflowLogoutMode.getValue());
        } catch (IllegalArgumentException e) {
            return LogoutReasonEnum.REPLACED;
        }
    }
}

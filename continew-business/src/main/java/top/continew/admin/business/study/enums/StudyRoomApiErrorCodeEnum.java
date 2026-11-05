package top.continew.admin.business.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 自习室小程序上游错误码
 * <p>
 * 上游返回的 {@code code} 是字符串业务码，不能直接当作系统错误码使用。当前响应拦截器仅使用
 * {@link #EMPTY_RESPONSE}，其余错误码为预留；非成功业务码的文案仍原样透传给前端。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomApiErrorCodeEnum implements BaseEnum<Integer> {

    /**
     * 绑定的小程序 token 已失效（上游返回 401；预留，当前未使用）
     */
    TOKEN_INVALID(40102, "自习室小程序 token 已失效，请重新绑定"),

    /**
     * 上游返回了非成功的业务码（预留，当前未使用）
     */
    UPSTREAM_ERROR(40103, "自习室小程序接口调用失败"),

    /**
     * 上游返回为空（网络异常、响应体为空等）
     */
    EMPTY_RESPONSE(40104, "自习室小程序接口返回为空"),

    /**
     * 账号异常（上游返回 SV99999，平台已限制预约；预留，当前未使用）
     */
    ACCOUNT_ABNORMAL(40105, "自习室账号异常，平台已限制预约");

    /**
     * 枚举值
     */
    private final Integer value;

    /**
     * 枚举描述
     */
    private final String description;
}

package top.continew.admin.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 第三方账号平台枚举
 *
 * @author bixiu
 * @since 1.3.0
 */
@Getter
@RequiredArgsConstructor
public enum SocialSourceEnum {

    /**
     * 微信
     */
    WECHAT_OPEN("微信"),

    /**
     * 码云
     */
    GITEE("码云"),

    /**
     * GitHub
     */
    GITHUB("GitHub"),;

    private final String description;
}

package top.continew.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 性别枚举
 *
 * @author bixiu
 * @since 1.0.0
 */
@Getter
@RequiredArgsConstructor
public enum GenderEnum implements BaseEnum<Integer> {

    /**
     * 未知
     */
    UNKNOWN(0, "未知"),

    /**
     * 男
     */
    MALE(1, "男"),

    /**
     * 女
     */
    FEMALE(2, "女"),;

    private final Integer value;
    private final String description;
}

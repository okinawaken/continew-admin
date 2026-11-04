package top.continew.admin.business.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

import java.util.Objects;

/**
 * 自动签到开关
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomAutoSignInEnableEnum implements BaseEnum<Integer> {

    /**
     * 关闭：不参与自动签到
     */
    DISABLED(0, "关闭"),

    /**
     * 开启：放号预约成功后自动签到
     */
    ENABLED(1, "开启");

    private final Integer value;

    private final String description;

    /**
     * 是否开启自动签到
     *
     * @param value 枚举值
     * @return 是否启用
     */
    public static boolean isEnabled(Integer value) {
        return Objects.equals(ENABLED.getValue(), value);
    }
}

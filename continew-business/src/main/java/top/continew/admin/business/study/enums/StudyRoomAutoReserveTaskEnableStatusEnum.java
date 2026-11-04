package top.continew.admin.business.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

import java.util.Objects;

/**
 * 自动预约任务启用状态
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomAutoReserveTaskEnableStatusEnum implements BaseEnum<Integer> {

    /**
     * 暂停：不参与派发，配置保留
     */
    PAUSED(0, "暂停"),

    /**
     * 启用：参与扫描与派发
     */
    ENABLED(1, "启用");

    private final Integer value;

    private final String description;

    /**
     * 是否启用
     *
     * @param value 枚举值
     * @return 是否启用
     */
    public static boolean isEnabled(Integer value) {
        return Objects.equals(ENABLED.getValue(), value);
    }
}

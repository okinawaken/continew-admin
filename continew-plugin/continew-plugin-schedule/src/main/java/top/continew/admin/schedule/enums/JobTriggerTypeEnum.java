package top.continew.admin.schedule.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 任务触发类型枚举
 *
 * @author bixiu
 * @since 3.2.0
 */
@Getter
@RequiredArgsConstructor
public enum JobTriggerTypeEnum implements BaseEnum<Integer> {

    /**
     * 固定时间
     */
    FIXED_TIME(2, "固定时间"),

    /**
     * CRON
     */
    CRON(3, "CRON");

    private final Integer value;
    private final String description;
}

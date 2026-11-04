package top.continew.admin.schedule.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 任务阻塞策略枚举
 *
 * @author Charles7c
 * @since 3.2.0
 */
@Getter
@RequiredArgsConstructor
public enum JobBlockStrategyEnum implements BaseEnum<Integer> {

    /**
     * 丢弃
     */
    DISCARD(1, "丢弃"),

    /**
     * 覆盖
     */
    COVER(2, "覆盖"),

    /**
     * 并行
     */
    PARALLEL(3, "并行"),;

    private final Integer value;
    private final String description;
}

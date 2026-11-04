package top.continew.admin.schedule.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 任务路由策略枚举
 *
 * @author Charles7c
 * @since 3.2.0
 */
@Getter
@RequiredArgsConstructor
public enum JobRouteStrategyEnum implements BaseEnum<Integer> {

    /**
     * 轮询
     */
    POLLING(4, "轮询"),

    /**
     * 随机
     */
    RANDOM(2, "随机"),

    /**
     * 一致性哈希
     */
    HASH(1, "一致性哈希"),

    /**
     * LRU
     */
    LRU(3, "LRU"),;

    private final Integer value;
    private final String description;
}

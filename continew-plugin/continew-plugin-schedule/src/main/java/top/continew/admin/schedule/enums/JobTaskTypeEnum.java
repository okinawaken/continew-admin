package top.continew.admin.schedule.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.admin.common.constant.UiConstants;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 任务类型枚举
 *
 * @author Charles7c
 * @since 3.2.0
 */
@Getter
@RequiredArgsConstructor
public enum JobTaskTypeEnum implements BaseEnum<Integer> {

    /**
     * 集群
     */
    CLUSTER(1, "集群", UiConstants.COLOR_PRIMARY),

    /**
     * 广播
     */
    BROADCAST(2, "广播", UiConstants.COLOR_PRIMARY),

    /**
     * 静态切片
     */
    SLICE(3, "静态切片", UiConstants.COLOR_PRIMARY),;

    private final Integer value;
    private final String description;
    private final String color;
}

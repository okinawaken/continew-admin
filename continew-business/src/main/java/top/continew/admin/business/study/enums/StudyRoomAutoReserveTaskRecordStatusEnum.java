package top.continew.admin.business.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 自动预约执行记录状态：跳过、成功、失败三种终态
 *
 * @author bixiu
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomAutoReserveTaskRecordStatusEnum implements BaseEnum<Integer> {

    /**
     * 跳过
     */
    SKIPPED(0, "跳过"),

    /**
     * 预约成功
     */
    SUCCESS(1, "成功"),

    /**
     * 执行失败
     */
    FAILED(2, "失败");

    private final Integer value;

    private final String description;
}

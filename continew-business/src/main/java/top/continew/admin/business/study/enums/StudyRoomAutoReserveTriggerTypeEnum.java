package top.continew.admin.business.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 自动预约触发方式
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomAutoReserveTriggerTypeEnum implements BaseEnum<Integer> {

    /**
     * 放号时刻由定时任务触发
     */
    SCHEDULED(0, "定时执行"),

    /**
     * 页面手动触发
     */
    MANUAL(1, "手动执行");

    private final Integer value;

    private final String description;
}

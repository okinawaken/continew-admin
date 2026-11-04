package top.continew.admin.business.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

import java.util.Objects;

/**
 * 预约状态（reserve/check 返回的 reserveState）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomReserveStatusEnum implements BaseEnum<Integer> {

    /**
     * 0 可预约
     */
    CAN(0, "可预约"),

    /**
     * 1 不可预约
     */
    CANNOT(1, "不可预约");

    private final Integer value;

    private final String description;

    /**
     * 是否可预约
     *
     * @param value 枚举值
     * @return 是否可预约
     */
    public static boolean isCanReserve(Integer value) {
        return Objects.equals(CAN.getValue(), value);
    }
}

package top.continew.admin.business.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

import java.util.Objects;

/**
 * 座位可预约状态（seat/list 返回的 seatReserveState）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomSeatStatusEnum implements BaseEnum<Integer> {

    /**
     * 10 空闲
     */
    FREE(10, "空闲"),

    /**
     * 20 部分时段已被预约
     */
    PART(20, "部分时段已被预约"),

    /**
     * 30 不可预约（vip / 未开放 / 已约满）
     */
    DISABLED(30, "不可预约");

    private final Integer value;

    private final String description;

    /**
     * 是否可预约（空闲、部分时段可约均可预约）
     *
     * @param value 枚举值
     * @return 是否可预约
     */
    public static boolean isCanReserve(Integer value) {
        return Objects.equals(FREE.getValue(), value) || Objects.equals(PART.getValue(), value);
    }
}

package top.continew.admin.business.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 自动签到过滤器执行顺序
 * <p>
 * 数值越小越先执行，供 {@code StudyRoomAutoSignFilter} 实现类声明自身顺序。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomAutoSignFilterOrderEnum implements BaseEnum<Integer> {

    /**
     * 待签到预约查询
     */
    PENDING_RESERVE(100, "待签到预约查询"),

    /**
     * 签到门店校验
     */
    VENUE(200, "签到门店校验"),

    /**
     * 签到扣费套餐查询
     */
    SIGN_IN_PACKAGE(300, "签到扣费套餐查询");

    /**
     * 枚举值，值越小越先执行
     */
    private final Integer value;

    /**
     * 枚举描述
     */
    private final String description;
}

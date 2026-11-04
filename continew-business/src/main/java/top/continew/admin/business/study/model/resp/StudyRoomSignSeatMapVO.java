package top.continew.admin.business.study.model.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomSignSeatMapResp 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StudyRoomSignSeatMapVO extends StudyRoomSeatMapVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 当前签到ID
     */
    private Long userSignId;

    /**
     * 签到扣费套餐
     */
    private StudyRoomUserPackageVO signInUserPackage;

    /**
     * 是否再签一个座位：1 是
     */
    private Integer signAnotherFlag;

    /**
     * 预约座位签到成功标志：1 已用预约座位直接完成签到
     * <p>
     * 仅签到选座接口（/sign/seat/list）可能返回，正常座位图为 null。
     */
    private Integer signInFlag;
}

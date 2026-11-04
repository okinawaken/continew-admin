package top.continew.admin.business.study.model.req;

import lombok.Data;

/**
 * 取消预约（/mp/user/userReserve/cancel）
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
public class StudyRoomUserReserveCancelClientReq {

    /**
     * 预约记录ID（取自提交预约返回的 data.id）（必填）
     */
    private Long userReserveId;
}

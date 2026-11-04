package top.continew.admin.business.study.client.http.model.req;

import lombok.Data;

/**
 * 取消预约（/mp/user/userReserve/cancel）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserReserveCancelReq {

    /**
     * 预约记录ID（取自提交预约返回的 data.id）（必填）
     */
    private Long userReserveId;
}

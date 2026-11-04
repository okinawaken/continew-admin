package top.continew.admin.business.study.client.http.model.req;

import lombok.Data;

/**
 * 取消请假（/mp/user/userLeave/cancelLeave）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserLeaveCancelClientReq {

    /**
     * 请假记录ID（必填）
     */
    private Long userLeaveId;
}

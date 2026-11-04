package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.model.req.StudyRoomUserLeaveCancelReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomUserLeaveConfirmReqDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserLeaveEnterRespDTO;

/**
 * 请假域：套餐请假
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomLeaveClient extends StudyRoomBaseClient {

    /**
     * 进入请假页（校验可请假套餐）
     *
     * @param userPackageId 参数 userPackageId
     * @return 响应数据
     */
    @Get(url = "/mp/user/userLeave/enterLeave")
    StudyRoomApiResultDTO<StudyRoomUserLeaveEnterRespDTO> userLeaveEnter(
        @Query(value = "userPackageId") Long userPackageId);

    /**
     * 确认请假
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/user/userLeave/confirmLeave")
    StudyRoomApiResultDTO<Integer> userLeaveConfirm(@JSONBody StudyRoomUserLeaveConfirmReqDTO form);

    /**
     * 取消请假
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/user/userLeave/cancelLeave")
    StudyRoomApiResultDTO<Integer> userLeaveCancel(@JSONBody StudyRoomUserLeaveCancelReqDTO form);

}

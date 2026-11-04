package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Var;
import top.continew.admin.business.study.model.req.StudyRoomReserveConfirmClientReq;
import top.continew.admin.business.study.model.req.StudyRoomReserveConfirmV1ClientReq;
import top.continew.admin.business.study.model.req.StudyRoomSeatQueryClientReq;
import top.continew.admin.business.study.model.req.StudyRoomSeatQueryV1ClientReq;
import top.continew.admin.business.study.model.req.StudyRoomUserReserveCancelClientReq;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomReserveAnotherCheckClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveCheckClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveConfirmClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomReserveDateClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatMapClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveCancelTipClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveListClientResp;
import top.continew.admin.business.study.constant.StudyRoomApiConstants;

/**
 * 预约域（显式用户上下文）：座位图、下单、我的预约、取消
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomReserveV2Client extends StudyRoomBaseClient {

    /**
     * 预约资格校验（是否有可预约套餐）
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/check")
    StudyRoomClientResult<StudyRoomReserveCheckClientResp> reserveCheck(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 可预约日期列表（默认返回今天起 N 天，N 由 sysConfig.reserveMaxDays 决定）
     *
     * @param venueId 门店 ID
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/v2/date/select")
    StudyRoomClientResult<StudyRoomReserveDateClientResp> reserveDateList(
        @Query(value = "venueId") Integer venueId,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 座位图（v2，推荐）：按日期 + 时间段查询门店全部座位
     * <p>
     * 时间段传空串表示查全天
     *
     * @param form 请求参数
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/v2/seat/list")
    StudyRoomClientResult<StudyRoomSeatMapClientResp> reserveV2SeatList(
        @JSONBody StudyRoomSeatQueryClientReq form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 提交预约（v2，推荐）
     * <p>
     * 实测返回 {@code data.id} 即 userReserveId，取消预约必须用它
     *
     * @param form 请求参数
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/v2/seat/confirm")
    StudyRoomClientResult<StudyRoomReserveConfirmClientResp> reserveV2SeatConfirm(
        @JSONBody StudyRoomReserveConfirmClientReq form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 座位图（v1，旧版）
     * <p>
     * 注意：v1 的时间参数格式是 {@code yyyy-MM-dd HH:mm}，不是 {@code HH:mm}
     *
     * @param form 请求参数
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/seat/list")
    StudyRoomClientResult<StudyRoomSeatMapClientResp> reserveSeatList(
        @JSONBody StudyRoomSeatQueryV1ClientReq form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 提交预约（v1，旧版）
     * <p>
     * 注意：v1 的时间参数格式是 {@code yyyy-MM-dd HH:mm}
     *
     * @param form 请求参数
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/seat/confirm")
    StudyRoomClientResult<StudyRoomReserveConfirmClientResp> reserveSeatConfirm(
        @JSONBody StudyRoomReserveConfirmV1ClientReq form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 是否还能再预约一个座位
     *
     * @param userReserveId 用户预约 ID
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/another/check")
    StudyRoomClientResult<StudyRoomReserveAnotherCheckClientResp> reserveAnotherCheck(
        @Query(value = "userReserveId") Long userReserveId,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 我的预约列表（一次性返回全部，无分页）
     *
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/userReserve/list")
    StudyRoomClientResult<StudyRoomUserReserveListClientResp> userReserveList(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 取消预约前的提示（实测返回 {@code {tipDesc, userReserveId}}）
     *
     * @param userReserveId 用户预约 ID
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/userReserve/cancel/tip")
    StudyRoomClientResult<StudyRoomUserReserveCancelTipClientResp> userReserveCancelTip(
        @Query(value = "userReserveId") Long userReserveId,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 取消预约
     * <p>
     * 实测返回 {@code data=1}（整数），不是对象
     *
     * @param form 请求参数
     * @param userId 用户 ID
     * @return 响应数据
     */
    @Post(url = "/mp/user/userReserve/cancel")
    StudyRoomClientResult<Integer> userReserveCancel(
        @JSONBody StudyRoomUserReserveCancelClientReq form,
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

}

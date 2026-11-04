package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.http.model.req.StudyRoomReserveConfirmReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomReserveConfirmV1Req;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSeatQueryReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSeatQueryV1Req;
import top.continew.admin.business.study.client.http.model.req.StudyRoomUserReserveCancelReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomApiResult;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveAnotherCheckResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveCheckResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveConfirmResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveDateResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSeatMapResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserReserveCancelTipResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserReserveListResp;

/**
 * 预约域：座位图、下单、我的预约、取消
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomReserveClient extends StudyRoomBaseClient {

    /**
     * 预约资格校验（是否有可预约套餐）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/check")
    StudyRoomApiResult<StudyRoomReserveCheckResp> reserveCheck();

    /**
     * 可预约日期列表（默认返回今天起 N 天，N 由 sysConfig.reserveMaxDays 决定）
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/v2/date/select")
    StudyRoomApiResult<StudyRoomReserveDateResp> reserveDateList(
        @Query(value = "venueId") Integer venueId);

    /**
     * 座位图（v2，推荐）：按日期 + 时间段查询门店全部座位
     * <p>
     * 时间段传空串表示查全天
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/v2/seat/list")
    StudyRoomApiResult<StudyRoomSeatMapResp> reserveV2SeatList(
        @JSONBody StudyRoomSeatQueryReq form);

    /**
     * 提交预约（v2，推荐）
     * <p>
     * 实测返回 {@code data.id} 即 userReserveId，取消预约必须用它
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/v2/seat/confirm")
    StudyRoomApiResult<StudyRoomReserveConfirmResp> reserveV2SeatConfirm(
        @JSONBody StudyRoomReserveConfirmReq form);

    /**
     * 座位图（v1，旧版）
     * <p>
     * 注意：v1 的时间参数格式是 {@code yyyy-MM-dd HH:mm}，不是 {@code HH:mm}
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/seat/list")
    StudyRoomApiResult<StudyRoomSeatMapResp> reserveSeatList(
        @JSONBody StudyRoomSeatQueryV1Req form);

    /**
     * 提交预约（v1，旧版）
     * <p>
     * 注意：v1 的时间参数格式是 {@code yyyy-MM-dd HH:mm}
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/seat/confirm")
    StudyRoomApiResult<StudyRoomReserveConfirmResp> reserveSeatConfirm(
        @JSONBody StudyRoomReserveConfirmV1Req form);

    /**
     * 是否还能再预约一个座位
     *
     * @param userReserveId 用户预约 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/another/check")
    StudyRoomApiResult<StudyRoomReserveAnotherCheckResp> reserveAnotherCheck(
        @Query(value = "userReserveId") Long userReserveId);

    /**
     * 我的预约列表（一次性返回全部，无分页）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/user/userReserve/list")
    StudyRoomApiResult<StudyRoomUserReserveListResp> userReserveList();

    /**
     * 取消预约前的提示（实测返回 {@code {tipDesc, userReserveId}}）
     *
     * @param userReserveId 用户预约 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/userReserve/cancel/tip")
    StudyRoomApiResult<StudyRoomUserReserveCancelTipResp> userReserveCancelTip(
        @Query(value = "userReserveId") Long userReserveId);

    /**
     * 取消预约
     * <p>
     * 实测返回 {@code data=1}（整数），不是对象
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/user/userReserve/cancel")
    StudyRoomApiResult<Integer> userReserveCancel(
        @JSONBody StudyRoomUserReserveCancelReq form);

}

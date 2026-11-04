package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.http.model.req.StudyRoomChangeSeatConfirmReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomChangeSeatReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomEspLightTurnReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomRankReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSeatConfirmReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSettlePackageReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSettlementReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSignOutReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSignSeatQueryReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomUserPackageConfirmReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomApiResult;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomChangeSeatConfirmResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomIndexResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomRankResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSeatConfirmResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSettlePackageResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSettlementResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSignAnotherCheckResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSignOutResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSignSeatListResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSignSeatMapResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserPackageConfirmResp;

import java.util.List;

/**
 * 签到域：签到、换座、签退、排行榜
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomSignClient extends StudyRoomBaseClient {

    /**
     * 签到首页：当前签到信息、附近门店、可选套餐
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @param venueId 门店 ID
     * @param bssid BSSID
     * @param ssid SSID
     * @return 响应数据
     */
    @Get(url = "/mp/study/index")
    StudyRoomApiResult<StudyRoomIndexResp> studyIndex(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat, @Query(value = "venueId") Integer venueId,
        @Query(value = "bssid") String bssid,
        @Query(value = "ssid") String ssid);

    /**
     * 签到选座
     * <p>
     * 该接口有两种响应：普通用户返回座位图（{@code code=200}）；存在待签到预约的用户由上游直接完成签到，
     * 返回 {@code code=SV0001} + 「预约座位XXX签到成功！」文案（{@code data=1}）。
     * 两种形态由 {@link StudyRoomSignSeatListResp} 统一承接。
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/seat/list")
    StudyRoomApiResult<StudyRoomSignSeatListResp> studySeatList(
        @JSONBody StudyRoomSignSeatQueryReq form);

    /**
     * 换座座位图
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/change/seat/list")
    StudyRoomApiResult<StudyRoomSignSeatMapResp> studyChangeSeatList(
        @JSONBody StudyRoomChangeSeatReq form);

    /**
     * 签退可选结算套餐（实测 {@code data} 是数组）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/settlePackage/select")
    StudyRoomApiResult<List<StudyRoomSettlePackageResp>> studySettlePackageSelect(
        @JSONBody StudyRoomSettlePackageReq form);

    /**
     * 是否可再签一个座位
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/study/signAnother/check")
    StudyRoomApiResult<StudyRoomSignAnotherCheckResp> studySignAnotherCheck(
        @Query(value = "venueId") Integer venueId);

    /**
     * 签退（扫码签退），返回结算结果
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/signOut")
    StudyRoomApiResult<StudyRoomSignOutResp> studySignOut(
        @JSONBody StudyRoomSignOutReq form);

    /**
     * 学习排行榜
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/rank/index")
    StudyRoomApiResult<StudyRoomRankResp> studyRank(@JSONBody StudyRoomRankReq form);

    /**
     * 签到选座确认（真正入座）
     * <p>
     * 与 {@link #studySeatList} 配对使用：先取座位图选座，再调本接口落座。
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/seat/confirm")
    StudyRoomApiResult<StudyRoomSeatConfirmResp> studySeatConfirm(
        @JSONBody StudyRoomSeatConfirmReq form);

    /**
     * 换座确认
     * <p>
     * 与 {@link #studyChangeSeatList} 配对使用。未签到时调用会返回「还未签到，不能换座」。
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/change/seat/confirm")
    StudyRoomApiResult<StudyRoomChangeSeatConfirmResp> studyChangeSeatConfirm(
        @JSONBody StudyRoomChangeSeatConfirmReq form);

    /**
     * 进入签退结算：返回结算明细 + 可选结算套餐
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/settlement")
    StudyRoomApiResult<StudyRoomSettlementResp> studySettlement(
        @JSONBody StudyRoomSettlementReq form);

    /**
     * 确认签退结算套餐（选套餐扣费）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/userPackage/confirm")
    StudyRoomApiResult<StudyRoomUserPackageConfirmResp> studyUserPackageConfirm(
        @JSONBody StudyRoomUserPackageConfirmReq form);

    /**
     * 座位灯控（开灯 / 关灯）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/espLight/turn")
    StudyRoomApiResult<Void> espLightTurn(@JSONBody StudyRoomEspLightTurnReq form);

}

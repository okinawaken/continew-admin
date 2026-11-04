package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.http.model.req.StudyRoomChangeSeatClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomChangeSeatConfirmClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomEspLightTurnClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomRankClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSeatConfirmClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSettlePackageClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSettlementClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSignOutClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSignSeatQueryClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomUserPackageConfirmClientReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomChangeSeatConfirmClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomIndexClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomRankClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSeatConfirmClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSettlePackageClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSettlementClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSignAnotherCheckClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSignOutClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSignSeatListClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSignSeatMapClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserPackageConfirmClientResp;

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
    StudyRoomClientResult<StudyRoomIndexClientResp> studyIndex(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat, @Query(value = "venueId") Integer venueId,
        @Query(value = "bssid") String bssid,
        @Query(value = "ssid") String ssid);

    /**
     * 签到选座
     * <p>
     * 该接口有两种响应：普通用户返回座位图（{@code code=200}）；存在待签到预约的用户由上游直接完成签到，
     * 返回 {@code code=SV0001} + 「预约座位XXX签到成功！」文案（{@code data=1}）。
     * 两种形态由 {@link StudyRoomSignSeatListClientResp} 统一承接。
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/seat/list")
    StudyRoomClientResult<StudyRoomSignSeatListClientResp> studySeatList(
        @JSONBody StudyRoomSignSeatQueryClientReq form);

    /**
     * 换座座位图
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/change/seat/list")
    StudyRoomClientResult<StudyRoomSignSeatMapClientResp> studyChangeSeatList(
        @JSONBody StudyRoomChangeSeatClientReq form);

    /**
     * 签退可选结算套餐（实测 {@code data} 是数组）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/settlePackage/select")
    StudyRoomClientResult<List<StudyRoomSettlePackageClientResp>> studySettlePackageSelect(
        @JSONBody StudyRoomSettlePackageClientReq form);

    /**
     * 是否可再签一个座位
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/study/signAnother/check")
    StudyRoomClientResult<StudyRoomSignAnotherCheckClientResp> studySignAnotherCheck(
        @Query(value = "venueId") Integer venueId);

    /**
     * 签退（扫码签退），返回结算结果
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/signOut")
    StudyRoomClientResult<StudyRoomSignOutClientResp> studySignOut(
        @JSONBody StudyRoomSignOutClientReq form);

    /**
     * 学习排行榜
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/rank/index")
    StudyRoomClientResult<StudyRoomRankClientResp> studyRank(@JSONBody StudyRoomRankClientReq form);

    /**
     * 签到选座确认（真正入座）
     * <p>
     * 与 {@link #studySeatList} 配对使用：先取座位图选座，再调本接口落座。
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/seat/confirm")
    StudyRoomClientResult<StudyRoomSeatConfirmClientResp> studySeatConfirm(
        @JSONBody StudyRoomSeatConfirmClientReq form);

    /**
     * 换座确认
     * <p>
     * 与 {@link #studyChangeSeatList} 配对使用。未签到时调用会返回「还未签到，不能换座」。
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/change/seat/confirm")
    StudyRoomClientResult<StudyRoomChangeSeatConfirmClientResp> studyChangeSeatConfirm(
        @JSONBody StudyRoomChangeSeatConfirmClientReq form);

    /**
     * 进入签退结算：返回结算明细 + 可选结算套餐
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/settlement")
    StudyRoomClientResult<StudyRoomSettlementClientResp> studySettlement(
        @JSONBody StudyRoomSettlementClientReq form);

    /**
     * 确认签退结算套餐（选套餐扣费）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/userPackage/confirm")
    StudyRoomClientResult<StudyRoomUserPackageConfirmClientResp> studyUserPackageConfirm(
        @JSONBody StudyRoomUserPackageConfirmClientReq form);

    /**
     * 座位灯控（开灯 / 关灯）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/espLight/turn")
    StudyRoomClientResult<Void> espLightTurn(@JSONBody StudyRoomEspLightTurnClientReq form);

}

package top.continew.admin.business.study.controller.client;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomSignClient;
import top.continew.admin.business.study.model.req.StudyRoomChangeSeatClientReq;
import top.continew.admin.business.study.model.req.StudyRoomChangeSeatConfirmClientReq;
import top.continew.admin.business.study.model.req.StudyRoomEspLightTurnClientReq;
import top.continew.admin.business.study.model.req.StudyRoomRankClientReq;
import top.continew.admin.business.study.model.req.StudyRoomSeatConfirmClientReq;
import top.continew.admin.business.study.model.req.StudyRoomSettlePackageClientReq;
import top.continew.admin.business.study.model.req.StudyRoomSettlementClientReq;
import top.continew.admin.business.study.model.req.StudyRoomSignOutClientReq;
import top.continew.admin.business.study.model.req.StudyRoomSignSeatQueryClientReq;
import top.continew.admin.business.study.model.req.StudyRoomUserPackageConfirmClientReq;
import top.continew.admin.business.study.model.resp.StudyRoomChangeSeatConfirmClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomIndexClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomRankClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatConfirmClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSettlePackageClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSettlementClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSignAnotherCheckClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSignOutClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSignSeatListClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSignSeatMapClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserPackageConfirmClientResp;
import top.continew.starter.web.model.R;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 自习室签到域接口：签到、换座、签退、排行榜
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/sign")
public class StudyRoomSignController {

    private final StudyRoomSignClient signClient;

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
    @SaCheckPermission("business:study:sign:list")
    @GetMapping(value = "/index")
    public R<StudyRoomIndexClientResp> studyIndex(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat,
        @RequestParam(name = "venueId", required = false) Integer venueId,
        @RequestParam(name = "bssid", required = false) String bssid,
        @RequestParam(name = "ssid", required = false) String ssid) {
        return R.ok(signClient.studyIndex(currentLng, currentLat, venueId, bssid, ssid).getData());
    }

    /**
     * 签到选座座位图
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @PostMapping(value = "/seat/list")
    public R<StudyRoomSignSeatListClientResp> studySeatList(
        @RequestBody StudyRoomSignSeatQueryClientReq form) {
        return R.ok(signClient.studySeatList(form).getData());
    }

    /**
     * 换座座位图
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @PostMapping(value = "/change/seat/list")
    public R<StudyRoomSignSeatMapClientResp> studyChangeSeatList(
        @RequestBody StudyRoomChangeSeatClientReq form) {
        return R.ok(signClient.studyChangeSeatList(form).getData());
    }

    /**
     * 签退可选结算套餐
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @PostMapping(value = "/settle-package/select")
    public R<List<StudyRoomSettlePackageClientResp>> studySettlePackageSelect(
        @RequestBody StudyRoomSettlePackageClientReq form) {
        return R.ok(signClient.studySettlePackageSelect(form).getData());
    }

    /**
     * 是否可再签一个座位
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @GetMapping(value = "/another/check")
    public R<StudyRoomSignAnotherCheckClientResp> studySignAnotherCheck(
        @RequestParam(value = "venueId") Integer venueId) {
        return R.ok(signClient.studySignAnotherCheck(venueId).getData());
    }

    /**
     * 签退（扫码签退），返回结算结果
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:create")
    @PostMapping(value = "/out")
    public R<StudyRoomSignOutClientResp> studySignOut(@RequestBody StudyRoomSignOutClientReq form) {
        return R.ok(signClient.studySignOut(form).getData());
    }

    /**
     * 学习排行榜
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @PostMapping(value = "/rank/index")
    public R<StudyRoomRankClientResp> studyRank(@RequestBody StudyRoomRankClientReq form) {
        return R.ok(signClient.studyRank(form).getData());
    }

    /**
     * 签到选座确认（真正入座）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:create")
    @PostMapping(value = "/seat/confirm")
    public R<StudyRoomSeatConfirmClientResp> studySeatConfirm(
        @RequestBody StudyRoomSeatConfirmClientReq form) {
        return R.ok(signClient.studySeatConfirm(form).getData());
    }

    /**
     * 换座确认
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:create")
    @PostMapping(value = "/change/seat/confirm")
    public R<StudyRoomChangeSeatConfirmClientResp> studyChangeSeatConfirm(
        @RequestBody StudyRoomChangeSeatConfirmClientReq form) {
        return R.ok(signClient.studyChangeSeatConfirm(form).getData());
    }

    /**
     * 进入签退结算：返回结算明细 + 可选结算套餐
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @PostMapping(value = "/settlement")
    public R<StudyRoomSettlementClientResp> studySettlement(
        @RequestBody StudyRoomSettlementClientReq form) {
        return R.ok(signClient.studySettlement(form).getData());
    }

    /**
     * 确认签退结算套餐（选套餐扣费）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:create")
    @PostMapping(value = "/user-package/confirm")
    public R<StudyRoomUserPackageConfirmClientResp> studyUserPackageConfirm(
        @RequestBody StudyRoomUserPackageConfirmClientReq form) {
        return R.ok(signClient.studyUserPackageConfirm(form).getData());
    }

    /**
     * 座位灯控（开灯 / 关灯）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:create")
    @PostMapping(value = "/esp-light/turn")
    public R<Void> espLightTurn(@Valid @RequestBody StudyRoomEspLightTurnClientReq form) {
        signClient.espLightTurn(form);
        return R.ok();
    }
}

package top.continew.admin.business.study.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomSignClient;
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
import top.continew.admin.business.study.model.resp.StudyRoomChangeSeatConfirmResp;
import top.continew.admin.business.study.model.resp.StudyRoomIndexResp;
import top.continew.admin.business.study.model.resp.StudyRoomRankResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatConfirmResp;
import top.continew.admin.business.study.model.resp.StudyRoomSettlePackageResp;
import top.continew.admin.business.study.model.resp.StudyRoomSettlementResp;
import top.continew.admin.business.study.model.resp.StudyRoomSignAnotherCheckResp;
import top.continew.admin.business.study.model.resp.StudyRoomSignOutResp;
import top.continew.admin.business.study.model.resp.StudyRoomSignSeatMapResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserPackageConfirmResp;
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
    public R<StudyRoomIndexResp> studyIndex(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat,
        @RequestParam(name = "venueId", required = false) Integer venueId,
        @RequestParam(name = "bssid", required = false) String bssid,
        @RequestParam(name = "ssid", required = false) String ssid) {
        StudyRoomIndexClientResp dto =
            signClient.studyIndex(currentLng, currentLat, venueId, bssid, ssid).getData();
        StudyRoomIndexResp vo = BeanUtil.copyProperties(dto, StudyRoomIndexResp.class);
        return R.ok(vo);
    }

    /**
     * 签到选座座位图
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @PostMapping(value = "/seat/list")
    public R<StudyRoomSignSeatMapResp> studySeatList(
        @RequestBody StudyRoomSignSeatQueryClientReq form) {
        StudyRoomSignSeatListClientResp dto = signClient.studySeatList(form).getData();
        StudyRoomSignSeatMapResp vo = BeanUtil.copyProperties(dto, StudyRoomSignSeatMapResp.class);
        return R.ok(vo);
    }

    /**
     * 换座座位图
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @PostMapping(value = "/change/seat/list")
    public R<StudyRoomSignSeatMapResp> studyChangeSeatList(
        @RequestBody StudyRoomChangeSeatClientReq form) {
        StudyRoomSignSeatMapClientResp dto = signClient.studyChangeSeatList(form).getData();
        StudyRoomSignSeatMapResp vo = BeanUtil.copyProperties(dto, StudyRoomSignSeatMapResp.class);
        return R.ok(vo);
    }

    /**
     * 签退可选结算套餐
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @PostMapping(value = "/settle-package/select")
    public R<List<StudyRoomSettlePackageResp>> studySettlePackageSelect(
        @RequestBody StudyRoomSettlePackageClientReq form) {
        List<StudyRoomSettlePackageClientResp> dto =
            signClient.studySettlePackageSelect(form).getData();
        List<StudyRoomSettlePackageResp> vo =
            BeanUtil.copyToList(dto, StudyRoomSettlePackageResp.class);
        return R.ok(vo);
    }

    /**
     * 是否可再签一个座位
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @GetMapping(value = "/another/check")
    public R<StudyRoomSignAnotherCheckResp> studySignAnotherCheck(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomSignAnotherCheckClientResp dto =
            signClient.studySignAnotherCheck(venueId).getData();
        StudyRoomSignAnotherCheckResp vo =
            BeanUtil.copyProperties(dto, StudyRoomSignAnotherCheckResp.class);
        return R.ok(vo);
    }

    /**
     * 签退（扫码签退），返回结算结果
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:create")
    @PostMapping(value = "/out")
    public R<StudyRoomSignOutResp> studySignOut(@RequestBody StudyRoomSignOutClientReq form) {
        StudyRoomSignOutClientResp dto = signClient.studySignOut(form).getData();
        StudyRoomSignOutResp vo = BeanUtil.copyProperties(dto, StudyRoomSignOutResp.class);
        return R.ok(vo);
    }

    /**
     * 学习排行榜
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @PostMapping(value = "/rank/index")
    public R<StudyRoomRankResp> studyRank(@RequestBody StudyRoomRankClientReq form) {
        StudyRoomRankClientResp dto = signClient.studyRank(form).getData();
        StudyRoomRankResp vo = BeanUtil.copyProperties(dto, StudyRoomRankResp.class);
        return R.ok(vo);
    }

    /**
     * 签到选座确认（真正入座）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:create")
    @PostMapping(value = "/seat/confirm")
    public R<StudyRoomSeatConfirmResp> studySeatConfirm(
        @RequestBody StudyRoomSeatConfirmClientReq form) {
        StudyRoomSeatConfirmClientResp dto = signClient.studySeatConfirm(form).getData();
        StudyRoomSeatConfirmResp vo = BeanUtil.copyProperties(dto, StudyRoomSeatConfirmResp.class);
        return R.ok(vo);
    }

    /**
     * 换座确认
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:create")
    @PostMapping(value = "/change/seat/confirm")
    public R<StudyRoomChangeSeatConfirmResp> studyChangeSeatConfirm(
        @RequestBody StudyRoomChangeSeatConfirmClientReq form) {
        StudyRoomChangeSeatConfirmClientResp dto =
            signClient.studyChangeSeatConfirm(form).getData();
        StudyRoomChangeSeatConfirmResp vo =
            BeanUtil.copyProperties(dto, StudyRoomChangeSeatConfirmResp.class);
        return R.ok(vo);
    }

    /**
     * 进入签退结算：返回结算明细 + 可选结算套餐
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:list")
    @PostMapping(value = "/settlement")
    public R<StudyRoomSettlementResp> studySettlement(
        @RequestBody StudyRoomSettlementClientReq form) {
        StudyRoomSettlementClientResp dto = signClient.studySettlement(form).getData();
        StudyRoomSettlementResp vo = BeanUtil.copyProperties(dto, StudyRoomSettlementResp.class);
        return R.ok(vo);
    }

    /**
     * 确认签退结算套餐（选套餐扣费）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:sign:create")
    @PostMapping(value = "/user-package/confirm")
    public R<StudyRoomUserPackageConfirmResp> studyUserPackageConfirm(
        @RequestBody StudyRoomUserPackageConfirmClientReq form) {
        StudyRoomUserPackageConfirmClientResp dto =
            signClient.studyUserPackageConfirm(form).getData();
        StudyRoomUserPackageConfirmResp vo =
            BeanUtil.copyProperties(dto, StudyRoomUserPackageConfirmResp.class);
        return R.ok(vo);
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

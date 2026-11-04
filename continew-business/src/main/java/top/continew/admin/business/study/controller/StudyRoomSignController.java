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
import top.continew.admin.business.study.model.resp.StudyRoomChangeSeatConfirmVO;
import top.continew.admin.business.study.model.resp.StudyRoomIndexVO;
import top.continew.admin.business.study.model.resp.StudyRoomRankVO;
import top.continew.admin.business.study.model.resp.StudyRoomSeatConfirmVO;
import top.continew.admin.business.study.model.resp.StudyRoomSettlePackageVO;
import top.continew.admin.business.study.model.resp.StudyRoomSettlementVO;
import top.continew.admin.business.study.model.resp.StudyRoomSignAnotherCheckVO;
import top.continew.admin.business.study.model.resp.StudyRoomSignOutVO;
import top.continew.admin.business.study.model.resp.StudyRoomSignSeatMapVO;
import top.continew.admin.business.study.model.resp.StudyRoomUserPackageConfirmVO;
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
    public R<StudyRoomIndexVO> studyIndex(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat,
        @RequestParam(name = "venueId", required = false) Integer venueId,
        @RequestParam(name = "bssid", required = false) String bssid,
        @RequestParam(name = "ssid", required = false) String ssid) {
        StudyRoomIndexResp dto =
            signClient.studyIndex(currentLng, currentLat, venueId, bssid, ssid).getData();
        StudyRoomIndexVO vo = BeanUtil.copyProperties(dto, StudyRoomIndexVO.class);
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
    public R<StudyRoomSignSeatMapVO> studySeatList(@RequestBody StudyRoomSignSeatQueryReq form) {
        StudyRoomSignSeatListResp dto = signClient.studySeatList(form).getData();
        StudyRoomSignSeatMapVO vo = BeanUtil.copyProperties(dto, StudyRoomSignSeatMapVO.class);
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
    public R<StudyRoomSignSeatMapVO> studyChangeSeatList(
        @RequestBody StudyRoomChangeSeatReq form) {
        StudyRoomSignSeatMapResp dto = signClient.studyChangeSeatList(form).getData();
        StudyRoomSignSeatMapVO vo = BeanUtil.copyProperties(dto, StudyRoomSignSeatMapVO.class);
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
    public R<List<StudyRoomSettlePackageVO>> studySettlePackageSelect(
        @RequestBody StudyRoomSettlePackageReq form) {
        List<StudyRoomSettlePackageResp> dto =
            signClient.studySettlePackageSelect(form).getData();
        List<StudyRoomSettlePackageVO> vo =
            BeanUtil.copyToList(dto, StudyRoomSettlePackageVO.class);
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
    public R<StudyRoomSignAnotherCheckVO> studySignAnotherCheck(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomSignAnotherCheckResp dto = signClient.studySignAnotherCheck(venueId).getData();
        StudyRoomSignAnotherCheckVO vo =
            BeanUtil.copyProperties(dto, StudyRoomSignAnotherCheckVO.class);
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
    public R<StudyRoomSignOutVO> studySignOut(@RequestBody StudyRoomSignOutReq form) {
        StudyRoomSignOutResp dto = signClient.studySignOut(form).getData();
        StudyRoomSignOutVO vo = BeanUtil.copyProperties(dto, StudyRoomSignOutVO.class);
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
    public R<StudyRoomRankVO> studyRank(@RequestBody StudyRoomRankReq form) {
        StudyRoomRankResp dto = signClient.studyRank(form).getData();
        StudyRoomRankVO vo = BeanUtil.copyProperties(dto, StudyRoomRankVO.class);
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
    public R<StudyRoomSeatConfirmVO> studySeatConfirm(
        @RequestBody StudyRoomSeatConfirmReq form) {
        StudyRoomSeatConfirmResp dto = signClient.studySeatConfirm(form).getData();
        StudyRoomSeatConfirmVO vo = BeanUtil.copyProperties(dto, StudyRoomSeatConfirmVO.class);
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
    public R<StudyRoomChangeSeatConfirmVO> studyChangeSeatConfirm(
        @RequestBody StudyRoomChangeSeatConfirmReq form) {
        StudyRoomChangeSeatConfirmResp dto = signClient.studyChangeSeatConfirm(form).getData();
        StudyRoomChangeSeatConfirmVO vo =
            BeanUtil.copyProperties(dto, StudyRoomChangeSeatConfirmVO.class);
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
    public R<StudyRoomSettlementVO> studySettlement(@RequestBody StudyRoomSettlementReq form) {
        StudyRoomSettlementResp dto = signClient.studySettlement(form).getData();
        StudyRoomSettlementVO vo = BeanUtil.copyProperties(dto, StudyRoomSettlementVO.class);
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
    public R<StudyRoomUserPackageConfirmVO> studyUserPackageConfirm(
        @RequestBody StudyRoomUserPackageConfirmReq form) {
        StudyRoomUserPackageConfirmResp dto = signClient.studyUserPackageConfirm(form).getData();
        StudyRoomUserPackageConfirmVO vo =
            BeanUtil.copyProperties(dto, StudyRoomUserPackageConfirmVO.class);
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
    public R<Void> espLightTurn(@Valid @RequestBody StudyRoomEspLightTurnReq form) {
        signClient.espLightTurn(form);
        return R.ok();
    }
}

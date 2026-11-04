/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import top.continew.admin.business.study.client.model.req.StudyRoomChangeSeatConfirmReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomChangeSeatReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomEspLightTurnReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomRankReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomSeatConfirmReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomSettlePackageReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomSettlementReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomSignOutReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomSignSeatQueryReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomUserPackageConfirmReqDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomChangeSeatConfirmRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomIndexRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomRankRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSeatConfirmRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSettlePackageRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSettlementRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSignAnotherCheckRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSignOutRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSignSeatListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSignSeatMapRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserPackageConfirmRespDTO;
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
        StudyRoomIndexRespDTO dto =
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
    public R<StudyRoomSignSeatMapVO> studySeatList(@RequestBody StudyRoomSignSeatQueryReqDTO form) {
        StudyRoomSignSeatListRespDTO dto = signClient.studySeatList(form).getData();
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
        @RequestBody StudyRoomChangeSeatReqDTO form) {
        StudyRoomSignSeatMapRespDTO dto = signClient.studyChangeSeatList(form).getData();
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
        @RequestBody StudyRoomSettlePackageReqDTO form) {
        List<StudyRoomSettlePackageRespDTO> dto =
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
        StudyRoomSignAnotherCheckRespDTO dto = signClient.studySignAnotherCheck(venueId).getData();
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
    public R<StudyRoomSignOutVO> studySignOut(@RequestBody StudyRoomSignOutReqDTO form) {
        StudyRoomSignOutRespDTO dto = signClient.studySignOut(form).getData();
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
    public R<StudyRoomRankVO> studyRank(@RequestBody StudyRoomRankReqDTO form) {
        StudyRoomRankRespDTO dto = signClient.studyRank(form).getData();
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
        @RequestBody StudyRoomSeatConfirmReqDTO form) {
        StudyRoomSeatConfirmRespDTO dto = signClient.studySeatConfirm(form).getData();
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
        @RequestBody StudyRoomChangeSeatConfirmReqDTO form) {
        StudyRoomChangeSeatConfirmRespDTO dto = signClient.studyChangeSeatConfirm(form).getData();
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
    public R<StudyRoomSettlementVO> studySettlement(@RequestBody StudyRoomSettlementReqDTO form) {
        StudyRoomSettlementRespDTO dto = signClient.studySettlement(form).getData();
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
        @RequestBody StudyRoomUserPackageConfirmReqDTO form) {
        StudyRoomUserPackageConfirmRespDTO dto = signClient.studyUserPackageConfirm(form).getData();
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
    public R<Void> espLightTurn(@Valid @RequestBody StudyRoomEspLightTurnReqDTO form) {
        signClient.espLightTurn(form);
        return R.ok();
    }
}

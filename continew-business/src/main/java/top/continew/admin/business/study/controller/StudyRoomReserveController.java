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
import top.continew.admin.business.study.client.http.StudyRoomReserveClient;
import top.continew.admin.business.study.client.model.req.StudyRoomReserveConfirmReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomReserveConfirmV1ReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomSeatQueryReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomSeatQueryV1ReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomUserReserveCancelReqDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomReserveAnotherCheckRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomReserveCheckRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomReserveConfirmRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomReserveDateRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSeatMapRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserReserveCancelTipRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserReserveListRespDTO;
import top.continew.admin.business.study.model.resp.StudyRoomReserveAnotherCheckVO;
import top.continew.admin.business.study.model.resp.StudyRoomReserveCheckVO;
import top.continew.admin.business.study.model.resp.StudyRoomReserveConfirmVO;
import top.continew.admin.business.study.model.resp.StudyRoomReserveDateVO;
import top.continew.admin.business.study.model.resp.StudyRoomSeatMapVO;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveCancelTipVO;
import top.continew.admin.business.study.model.resp.StudyRoomUserReserveListVO;
import top.continew.starter.web.model.R;

/**
 * 自习室预约域接口：座位图、下单、我的预约、取消
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/reserve")
public class StudyRoomReserveController {

    private final StudyRoomReserveClient reserveClient;

    /**
     * 预约资格校验（是否有可预约套餐）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/check")
    public R<StudyRoomReserveCheckVO> reserveCheck() {
        StudyRoomReserveCheckRespDTO dto = reserveClient.reserveCheck().getData();
        StudyRoomReserveCheckVO vo = BeanUtil.copyProperties(dto, StudyRoomReserveCheckVO.class);
        return R.ok(vo);
    }

    /**
     * 可预约日期列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/v2/date/select")
    public R<StudyRoomReserveDateVO> reserveDateList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomReserveDateRespDTO dto = reserveClient.reserveDateList(venueId).getData();
        StudyRoomReserveDateVO vo = BeanUtil.copyProperties(dto, StudyRoomReserveDateVO.class);
        return R.ok(vo);
    }

    /**
     * 座位图（v2，推荐）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @PostMapping(value = "/v2/seat/list")
    public R<StudyRoomSeatMapVO> reserveV2SeatList(@RequestBody StudyRoomSeatQueryReqDTO form) {
        StudyRoomSeatMapRespDTO dto = reserveClient.reserveV2SeatList(form).getData();
        StudyRoomSeatMapVO vo = BeanUtil.copyProperties(dto, StudyRoomSeatMapVO.class);
        return R.ok(vo);
    }

    /**
     * 提交预约（v2，推荐）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:create")
    @PostMapping(value = "/v2/seat/confirm")
    public R<StudyRoomReserveConfirmVO> reserveV2SeatConfirm(
        @RequestBody StudyRoomReserveConfirmReqDTO form) {
        StudyRoomReserveConfirmRespDTO dto = reserveClient.reserveV2SeatConfirm(form).getData();
        StudyRoomReserveConfirmVO vo =
            BeanUtil.copyProperties(dto, StudyRoomReserveConfirmVO.class);
        return R.ok(vo);
    }

    /**
     * 座位图（v1，旧版，时间格式 {@code yyyy-MM-dd HH:mm}）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @PostMapping(value = "/seat/list")
    public R<StudyRoomSeatMapVO> reserveSeatList(@RequestBody StudyRoomSeatQueryV1ReqDTO form) {
        StudyRoomSeatMapRespDTO dto = reserveClient.reserveSeatList(form).getData();
        StudyRoomSeatMapVO vo = BeanUtil.copyProperties(dto, StudyRoomSeatMapVO.class);
        return R.ok(vo);
    }

    /**
     * 提交预约（v1，旧版，时间格式 {@code yyyy-MM-dd HH:mm}）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:create")
    @PostMapping(value = "/seat/confirm")
    public R<StudyRoomReserveConfirmVO> reserveSeatConfirm(
        @RequestBody StudyRoomReserveConfirmV1ReqDTO form) {
        StudyRoomReserveConfirmRespDTO dto = reserveClient.reserveSeatConfirm(form).getData();
        StudyRoomReserveConfirmVO vo =
            BeanUtil.copyProperties(dto, StudyRoomReserveConfirmVO.class);
        return R.ok(vo);
    }

    /**
     * 是否还能再预约一个座位
     *
     * @param userReserveId 用户预约 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/another/check")
    public R<StudyRoomReserveAnotherCheckVO> reserveAnotherCheck(
        @RequestParam(value = "userReserveId") Long userReserveId) {
        StudyRoomReserveAnotherCheckRespDTO dto =
            reserveClient.reserveAnotherCheck(userReserveId).getData();
        StudyRoomReserveAnotherCheckVO vo =
            BeanUtil.copyProperties(dto, StudyRoomReserveAnotherCheckVO.class);
        return R.ok(vo);
    }

    /**
     * 我的预约列表（一次性返回全部，无分页）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/user/list")
    public R<StudyRoomUserReserveListVO> userReserveList() {
        StudyRoomUserReserveListRespDTO dto = reserveClient.userReserveList().getData();
        StudyRoomUserReserveListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomUserReserveListVO.class);
        return R.ok(vo);
    }

    /**
     * 取消预约前的提示
     *
     * @param userReserveId 用户预约 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:list")
    @GetMapping(value = "/user/cancel/tip")
    public R<StudyRoomUserReserveCancelTipVO> userReserveCancelTip(
        @RequestParam(value = "userReserveId") Long userReserveId) {
        StudyRoomUserReserveCancelTipRespDTO dto =
            reserveClient.userReserveCancelTip(userReserveId).getData();
        StudyRoomUserReserveCancelTipVO vo =
            BeanUtil.copyProperties(dto, StudyRoomUserReserveCancelTipVO.class);
        return R.ok(vo);
    }

    /**
     * 取消预约
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:reserve:delete")
    @PostMapping(value = "/user/cancel")
    public R<Integer> userReserveCancel(@RequestBody StudyRoomUserReserveCancelReqDTO form) {
        Integer data = reserveClient.userReserveCancel(form).getData();
        return R.ok(data);
    }
}

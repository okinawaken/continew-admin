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
import top.continew.admin.business.study.client.http.StudyRoomMiscClient;
import top.continew.admin.business.study.client.model.req.StudyRoomCabinetLeaseCommitReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomCabinetWifiOpenReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomDouYinVerifyReqDTO;
import top.continew.admin.business.study.client.model.req.StudyRoomReceiptConsumeReqDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetGridListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetGridPriceRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetLeaseCommitRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetLeaseOrderListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetLeaseOverduePayRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetUserGridListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomCabinetVenueListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomConsumeResultRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomWxPayParamRespDTO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetGridListVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetGridPriceVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetLeaseCommitVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetLeaseOrderListVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetLeaseOverduePayVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetUserGridListVO;
import top.continew.admin.business.study.model.resp.StudyRoomCabinetVenueListVO;
import top.continew.admin.business.study.model.resp.StudyRoomConsumeResultVO;
import top.continew.admin.business.study.model.resp.StudyRoomWxPayParamVO;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.web.model.R;

/**
 * 自习室其他域接口：储物柜、团购券核销
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/misc")
public class StudyRoomMiscController {

    private final StudyRoomMiscClient miscClient;

    /**
     * 储物柜租赁订单（无业务参数）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:list")
    @PostMapping(value = "/cabinet/lease/order/list")
    public R<StudyRoomCabinetLeaseOrderListVO> cabinetLeaseOrderList() {
        StudyRoomCabinetLeaseOrderListRespDTO dto = miscClient.cabinetLeaseOrderList().getData();
        StudyRoomCabinetLeaseOrderListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetLeaseOrderListVO.class);
        return R.ok(vo);
    }

    /**
     * 储物柜租赁支付（参数走 query，leaseId 必须是整数）
     *
     * @param leaseId 租约 ID
     * @param userId 用户 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/cabinet/lease/pay")
    public R<StudyRoomWxPayParamVO> cabinetLeasePay(
        @RequestParam(value = "leaseId") Long leaseId,
        @RequestParam(value = "userId") Long userId) {
        StudyRoomWxPayParamRespDTO dto = miscClient.cabinetLeasePay(leaseId, userId).getData();
        StudyRoomWxPayParamVO vo = BeanUtil.copyProperties(dto, StudyRoomWxPayParamVO.class);
        return R.ok(vo);
    }

    /**
     * 储物柜下单，返回租约 ID
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/cabinet/lease/commit")
    public R<StudyRoomCabinetLeaseCommitVO> cabinetLeaseCommit(
        @RequestBody StudyRoomCabinetLeaseCommitReqDTO form) {
        StudyRoomCabinetLeaseCommitRespDTO dto = miscClient.cabinetLeaseCommit(form).getData();
        StudyRoomCabinetLeaseCommitVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetLeaseCommitVO.class);
        return R.ok(vo);
    }

    /**
     * 储物柜逾期支付（参数走 query）
     *
     * @param leaseId 租约 ID
     * @param userCode 用户编码
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/cabinet/lease/overdue/pay")
    public R<StudyRoomCabinetLeaseOverduePayVO> cabinetLeaseOverduePay(
        @RequestParam(value = "leaseId") Long leaseId,
        @RequestParam(value = "userCode") String userCode) {
        StudyRoomCabinetLeaseOverduePayRespDTO dto =
            miscClient.cabinetLeaseOverduePay(leaseId, userCode).getData();
        StudyRoomCabinetLeaseOverduePayVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetLeaseOverduePayVO.class);
        return R.ok(vo);
    }

    /**
     * 储物柜自动退租（按最近一次租约）
     *
     * @param lastLeaseId 最近一次租约 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @GetMapping(value = "/cabinet/lease/auto-checkout")
    public R<Void> cabinetLeaseAutoCheckout(@RequestParam(value = "lastLeaseId") Long lastLeaseId) {
        miscClient.cabinetLeaseAutoCheckout(lastLeaseId);
        return R.ok();
    }

    /**
     * 储物柜开锁（WiFi 开锁，按柜格）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/cabinet/wifi/open")
    public R<Void> cabinetWifiOpen(@RequestBody StudyRoomCabinetWifiOpenReqDTO form) {
        miscClient.cabinetWifiOpen(form);
        return R.ok();
    }

    /**
     * 门店的储物柜列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:list")
    @GetMapping(value = "/cabinet/venue/list")
    public R<StudyRoomCabinetVenueListVO> cabinetVenueList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomCabinetVenueListRespDTO dto = miscClient.cabinetVenueList(venueId).getData();
        StudyRoomCabinetVenueListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetVenueListVO.class);
        return R.ok(vo);
    }

    /**
     * 柜体的格口列表
     *
     * @param cabinetId 柜体 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:list")
    @GetMapping(value = "/cabinet/grid/list")
    public R<StudyRoomCabinetGridListVO> cabinetGridList(
        @RequestParam(value = "cabinetId") Integer cabinetId) {
        StudyRoomCabinetGridListRespDTO dto = miscClient.cabinetGridList(cabinetId).getData();
        StudyRoomCabinetGridListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetGridListVO.class);
        return R.ok(vo);
    }

    /**
     * 我当前已租的柜格列表
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:list")
    @GetMapping(value = "/cabinet/user/grid/list")
    public R<StudyRoomCabinetUserGridListVO> cabinetUserGridList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomCabinetUserGridListRespDTO dto = miscClient.cabinetUserGridList(venueId).getData();
        StudyRoomCabinetUserGridListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetUserGridListVO.class);
        return R.ok(vo);
    }

    /**
     * 柜格定价
     *
     * @param gridId 格口 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:list")
    @GetMapping(value = "/cabinet/grid/price")
    public R<StudyRoomCabinetGridPriceVO> cabinetGridPrice(
        @RequestParam(value = "gridId") Integer gridId) {
        StudyRoomCabinetGridPriceRespDTO dto = miscClient.cabinetGridPrice(gridId).getData();
        StudyRoomCabinetGridPriceVO vo =
            BeanUtil.copyProperties(dto, StudyRoomCabinetGridPriceVO.class);
        return R.ok(vo);
    }

    /**
     * 美团团购券核销
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/receipt/consume")
    public R<StudyRoomConsumeResultVO> receiptConsume(
        @RequestBody StudyRoomReceiptConsumeReqDTO form) {
        StudyRoomConsumeResultRespDTO dto = miscClient.receiptConsume(form).getData();
        StudyRoomConsumeResultVO vo = BeanUtil.copyProperties(dto, StudyRoomConsumeResultVO.class);
        return R.ok(vo);
    }

    /**
     * 抖音券核销
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/douyin/verify")
    public R<StudyRoomConsumeResultVO> douYinVerify(@RequestBody StudyRoomDouYinVerifyReqDTO form) {
        StudyRoomConsumeResultRespDTO dto = miscClient.douYinVerify(form).getData();
        StudyRoomConsumeResultVO vo = BeanUtil.copyProperties(dto, StudyRoomConsumeResultVO.class);
        return R.ok(vo);
    }

    /**
     * 图片上传（当前不支持）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:misc:create")
    @PostMapping(value = "/image/upload")
    public R<Void> uploadImage() {
        throw new BusinessException("暂不支持文件上传");
    }
}

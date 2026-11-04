package top.continew.admin.business.study.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.client.http.StudyRoomPackageClient;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyPackageConfirmClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyPayOrderClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyV2PackageConfirmClientReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyV2PackageListClientReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyOrderClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyPackageListClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyV2PackageDetailClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyV2PackageLabelClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyV2PackageListClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyV2PackageOptClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserOrderListClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserPackageListClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomWxPayParamClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomWxPayQueryClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyOrderResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyPackageListResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageDetailResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageLabelResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageListResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageOptResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserOrderListResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserPackageListResp;
import top.continew.admin.business.study.model.resp.StudyRoomWxPayParamResp;
import top.continew.admin.business.study.model.resp.StudyRoomWxPayQueryResp;
import top.continew.starter.web.model.R;

/**
 * 自习室套餐域接口：套餐浏览、下单、支付、我的订单与套餐
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/package")
public class StudyRoomPackageController {

    private final StudyRoomPackageClient packageClient;

    /**
     * 套餐列表（旧版，按区域分组）
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/list")
    public R<StudyRoomBuyPackageListResp> buyPackageList() {
        StudyRoomBuyPackageListClientResp dto = packageClient.buyPackageList().getData();
        StudyRoomBuyPackageListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomBuyPackageListResp.class);
        return R.ok(vo);
    }

    /**
     * 套餐列表（v2，按门店/区域/类型筛选）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @PostMapping(value = "/v2/list")
    public R<StudyRoomBuyV2PackageListResp> buyV2PackageList(
        @RequestBody StudyRoomBuyV2PackageListClientReq form) {
        StudyRoomBuyV2PackageListClientResp dto = packageClient.buyV2PackageList(form).getData();
        StudyRoomBuyV2PackageListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomBuyV2PackageListResp.class);
        return R.ok(vo);
    }

    /**
     * 套餐筛选项（门店、类型、标签）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/v2/opt-list")
    public R<StudyRoomBuyV2PackageOptResp> buyV2PackageOptList(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat) {
        StudyRoomBuyV2PackageOptClientResp dto =
            packageClient.buyV2PackageOptList(currentLng, currentLat).getData();
        StudyRoomBuyV2PackageOptResp vo =
            BeanUtil.copyProperties(dto, StudyRoomBuyV2PackageOptResp.class);
        return R.ok(vo);
    }

    /**
     * 套餐标签 / 座位区域筛选项
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/v2/label/select-list")
    public R<StudyRoomBuyV2PackageLabelResp> buyV2PackageLabelList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomBuyV2PackageLabelClientResp dto =
            packageClient.buyV2PackageLabelList(venueId).getData();
        StudyRoomBuyV2PackageLabelResp vo =
            BeanUtil.copyProperties(dto, StudyRoomBuyV2PackageLabelResp.class);
        return R.ok(vo);
    }

    /**
     * 套餐详情（v2）
     *
     * @param packageId 套餐 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/v2/detail/{packageId}")
    public R<StudyRoomBuyV2PackageDetailResp> buyV2PackageDetail(
        @PathVariable(value = "packageId") Long packageId) {
        StudyRoomBuyV2PackageDetailClientResp dto =
            packageClient.buyV2PackageDetail(packageId).getData();
        StudyRoomBuyV2PackageDetailResp vo =
            BeanUtil.copyProperties(dto, StudyRoomBuyV2PackageDetailResp.class);
        return R.ok(vo);
    }

    /**
     * 确认下单（旧版）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:create")
    @PostMapping(value = "/confirm")
    public R<StudyRoomBuyOrderResp> buyPackageConfirm(
        @RequestBody StudyRoomBuyPackageConfirmClientReq form) {
        StudyRoomBuyOrderClientResp dto = packageClient.buyPackageConfirm(form).getData();
        StudyRoomBuyOrderResp vo = BeanUtil.copyProperties(dto, StudyRoomBuyOrderResp.class);
        return R.ok(vo);
    }

    /**
     * 确认下单（v2）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:create")
    @PostMapping(value = "/v2/confirm")
    public R<StudyRoomBuyOrderResp> buyV2PackageConfirm(
        @RequestBody StudyRoomBuyV2PackageConfirmClientReq form) {
        StudyRoomBuyOrderClientResp dto = packageClient.buyV2PackageConfirm(form).getData();
        StudyRoomBuyOrderResp vo = BeanUtil.copyProperties(dto, StudyRoomBuyOrderResp.class);
        return R.ok(vo);
    }

    /**
     * 微信支付下单（返回小程序 wx.requestPayment 所需参数）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:create")
    @PostMapping(value = "/pay-order")
    public R<StudyRoomWxPayParamResp> buyPackagePayOrder(
        @RequestBody StudyRoomBuyPayOrderClientReq form) {
        StudyRoomWxPayParamClientResp dto = packageClient.buyPackagePayOrder(form).getData();
        StudyRoomWxPayParamResp vo = BeanUtil.copyProperties(dto, StudyRoomWxPayParamResp.class);
        return R.ok(vo);
    }

    /**
     * 我的订单
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/user/order/list")
    public R<StudyRoomUserOrderListResp> userOrderList() {
        StudyRoomUserOrderListClientResp dto = packageClient.userOrderList().getData();
        StudyRoomUserOrderListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomUserOrderListResp.class);
        return R.ok(vo);
    }

    /**
     * 我的套餐
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/user/package/list")
    public R<StudyRoomUserPackageListResp> userPackageList() {
        StudyRoomUserPackageListClientResp dto = packageClient.userPackageList().getData();
        StudyRoomUserPackageListResp vo =
            BeanUtil.copyProperties(dto, StudyRoomUserPackageListResp.class);
        return R.ok(vo);
    }

    /**
     * 微信支付状态查询（按支付单 ID）
     *
     * @param payId 支付单 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @PostMapping(value = "/wx/query/status/{payId}")
    public R<StudyRoomWxPayQueryResp> wxPayQueryStatus(@PathVariable(value = "payId") Long payId) {
        StudyRoomWxPayQueryClientResp dto = packageClient.wxPayQueryStatus(payId).getData();
        StudyRoomWxPayQueryResp vo = BeanUtil.copyProperties(dto, StudyRoomWxPayQueryResp.class);
        return R.ok(vo);
    }
}

package top.continew.admin.business.study.controller.client;

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
import top.continew.admin.business.study.model.req.StudyRoomBuyPackageConfirmClientReq;
import top.continew.admin.business.study.model.req.StudyRoomBuyPayOrderClientReq;
import top.continew.admin.business.study.model.req.StudyRoomBuyV2PackageConfirmClientReq;
import top.continew.admin.business.study.model.req.StudyRoomBuyV2PackageListClientReq;
import top.continew.admin.business.study.model.resp.StudyRoomBuyOrderClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyPackageListClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageDetailClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageLabelClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageListClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageOptClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserOrderListClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomUserPackageListClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomWxPayParamClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomWxPayParamResp;
import top.continew.admin.business.study.model.resp.StudyRoomWxPayQueryClientResp;
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
    public R<StudyRoomBuyPackageListClientResp> buyPackageList() {
        StudyRoomBuyPackageListClientResp dto = packageClient.buyPackageList().getData();
        return R.ok(dto);
    }

    /**
     * 套餐列表（v2，按门店/区域/类型筛选）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @PostMapping(value = "/v2/list")
    public R<StudyRoomBuyV2PackageListClientResp> buyV2PackageList(
        @RequestBody StudyRoomBuyV2PackageListClientReq form) {
        StudyRoomBuyV2PackageListClientResp dto = packageClient.buyV2PackageList(form).getData();
        return R.ok(dto);
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
    public R<StudyRoomBuyV2PackageOptClientResp> buyV2PackageOptList(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat) {
        StudyRoomBuyV2PackageOptClientResp dto =
            packageClient.buyV2PackageOptList(currentLng, currentLat).getData();
        return R.ok(dto);
    }

    /**
     * 套餐标签 / 座位区域筛选项
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/v2/label/select-list")
    public R<StudyRoomBuyV2PackageLabelClientResp> buyV2PackageLabelList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomBuyV2PackageLabelClientResp dto =
            packageClient.buyV2PackageLabelList(venueId).getData();
        return R.ok(dto);
    }

    /**
     * 套餐详情（v2）
     *
     * @param packageId 套餐 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/v2/detail/{packageId}")
    public R<StudyRoomBuyV2PackageDetailClientResp> buyV2PackageDetail(
        @PathVariable(value = "packageId") Long packageId) {
        StudyRoomBuyV2PackageDetailClientResp dto =
            packageClient.buyV2PackageDetail(packageId).getData();
        return R.ok(dto);
    }

    /**
     * 确认下单（旧版）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:create")
    @PostMapping(value = "/confirm")
    public R<StudyRoomBuyOrderClientResp> buyPackageConfirm(
        @RequestBody StudyRoomBuyPackageConfirmClientReq form) {
        StudyRoomBuyOrderClientResp dto = packageClient.buyPackageConfirm(form).getData();
        return R.ok(dto);
    }

    /**
     * 确认下单（v2）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:create")
    @PostMapping(value = "/v2/confirm")
    public R<StudyRoomBuyOrderClientResp> buyV2PackageConfirm(
        @RequestBody StudyRoomBuyV2PackageConfirmClientReq form) {
        StudyRoomBuyOrderClientResp dto = packageClient.buyV2PackageConfirm(form).getData();
        return R.ok(dto);
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
    public R<StudyRoomUserOrderListClientResp> userOrderList() {
        StudyRoomUserOrderListClientResp dto = packageClient.userOrderList().getData();
        return R.ok(dto);
    }

    /**
     * 我的套餐
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/user/package/list")
    public R<StudyRoomUserPackageListClientResp> userPackageList() {
        StudyRoomUserPackageListClientResp dto = packageClient.userPackageList().getData();
        return R.ok(dto);
    }

    /**
     * 微信支付状态查询（按支付单 ID）
     *
     * @param payId 支付单 ID
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @PostMapping(value = "/wx/query/status/{payId}")
    public R<StudyRoomWxPayQueryClientResp> wxPayQueryStatus(
        @PathVariable(value = "payId") Long payId) {
        StudyRoomWxPayQueryClientResp dto = packageClient.wxPayQueryStatus(payId).getData();
        return R.ok(dto);
    }
}

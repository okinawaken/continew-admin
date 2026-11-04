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
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyPackageConfirmReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyPayOrderReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyV2PackageConfirmReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyV2PackageListReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyOrderResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyPackageListResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyV2PackageDetailResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyV2PackageLabelResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyV2PackageListResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomBuyV2PackageOptResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserOrderListResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomUserPackageListResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomWxPayParamResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomWxPayQueryResp;
import top.continew.admin.business.study.model.resp.StudyRoomBuyOrderVO;
import top.continew.admin.business.study.model.resp.StudyRoomBuyPackageListVO;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageDetailVO;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageLabelVO;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageListVO;
import top.continew.admin.business.study.model.resp.StudyRoomBuyV2PackageOptVO;
import top.continew.admin.business.study.model.resp.StudyRoomUserOrderListVO;
import top.continew.admin.business.study.model.resp.StudyRoomUserPackageListVO;
import top.continew.admin.business.study.model.resp.StudyRoomWxPayParamVO;
import top.continew.admin.business.study.model.resp.StudyRoomWxPayQueryVO;
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
    public R<StudyRoomBuyPackageListVO> buyPackageList() {
        StudyRoomBuyPackageListResp dto = packageClient.buyPackageList().getData();
        StudyRoomBuyPackageListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomBuyPackageListVO.class);
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
    public R<StudyRoomBuyV2PackageListVO> buyV2PackageList(
        @RequestBody StudyRoomBuyV2PackageListReq form) {
        StudyRoomBuyV2PackageListResp dto = packageClient.buyV2PackageList(form).getData();
        StudyRoomBuyV2PackageListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomBuyV2PackageListVO.class);
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
    public R<StudyRoomBuyV2PackageOptVO> buyV2PackageOptList(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat) {
        StudyRoomBuyV2PackageOptResp dto =
            packageClient.buyV2PackageOptList(currentLng, currentLat).getData();
        StudyRoomBuyV2PackageOptVO vo =
            BeanUtil.copyProperties(dto, StudyRoomBuyV2PackageOptVO.class);
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
    public R<StudyRoomBuyV2PackageLabelVO> buyV2PackageLabelList(
        @RequestParam(value = "venueId") Integer venueId) {
        StudyRoomBuyV2PackageLabelResp dto =
            packageClient.buyV2PackageLabelList(venueId).getData();
        StudyRoomBuyV2PackageLabelVO vo =
            BeanUtil.copyProperties(dto, StudyRoomBuyV2PackageLabelVO.class);
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
    public R<StudyRoomBuyV2PackageDetailVO> buyV2PackageDetail(
        @PathVariable(value = "packageId") Long packageId) {
        StudyRoomBuyV2PackageDetailResp dto =
            packageClient.buyV2PackageDetail(packageId).getData();
        StudyRoomBuyV2PackageDetailVO vo =
            BeanUtil.copyProperties(dto, StudyRoomBuyV2PackageDetailVO.class);
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
    public R<StudyRoomBuyOrderVO> buyPackageConfirm(
        @RequestBody StudyRoomBuyPackageConfirmReq form) {
        StudyRoomBuyOrderResp dto = packageClient.buyPackageConfirm(form).getData();
        StudyRoomBuyOrderVO vo = BeanUtil.copyProperties(dto, StudyRoomBuyOrderVO.class);
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
    public R<StudyRoomBuyOrderVO> buyV2PackageConfirm(
        @RequestBody StudyRoomBuyV2PackageConfirmReq form) {
        StudyRoomBuyOrderResp dto = packageClient.buyV2PackageConfirm(form).getData();
        StudyRoomBuyOrderVO vo = BeanUtil.copyProperties(dto, StudyRoomBuyOrderVO.class);
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
    public R<StudyRoomWxPayParamVO> buyPackagePayOrder(
        @RequestBody StudyRoomBuyPayOrderReq form) {
        StudyRoomWxPayParamResp dto = packageClient.buyPackagePayOrder(form).getData();
        StudyRoomWxPayParamVO vo = BeanUtil.copyProperties(dto, StudyRoomWxPayParamVO.class);
        return R.ok(vo);
    }

    /**
     * 我的订单
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/user/order/list")
    public R<StudyRoomUserOrderListVO> userOrderList() {
        StudyRoomUserOrderListResp dto = packageClient.userOrderList().getData();
        StudyRoomUserOrderListVO vo = BeanUtil.copyProperties(dto, StudyRoomUserOrderListVO.class);
        return R.ok(vo);
    }

    /**
     * 我的套餐
     *
     * @return 响应数据
     */
    @SaCheckPermission("business:study:package:list")
    @GetMapping(value = "/user/package/list")
    public R<StudyRoomUserPackageListVO> userPackageList() {
        StudyRoomUserPackageListResp dto = packageClient.userPackageList().getData();
        StudyRoomUserPackageListVO vo =
            BeanUtil.copyProperties(dto, StudyRoomUserPackageListVO.class);
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
    public R<StudyRoomWxPayQueryVO> wxPayQueryStatus(@PathVariable(value = "payId") Long payId) {
        StudyRoomWxPayQueryResp dto = packageClient.wxPayQueryStatus(payId).getData();
        StudyRoomWxPayQueryVO vo = BeanUtil.copyProperties(dto, StudyRoomWxPayQueryVO.class);
        return R.ok(vo);
    }
}

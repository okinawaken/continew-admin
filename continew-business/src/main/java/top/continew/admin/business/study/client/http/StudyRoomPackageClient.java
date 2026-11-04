package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Var;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyPackageConfirmReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyPayOrderReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyV2PackageConfirmReq;
import top.continew.admin.business.study.client.http.model.req.StudyRoomBuyV2PackageListReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomApiResult;
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

/**
 * 套餐域：套餐浏览、下单、支付、我的订单与套餐
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomPackageClient extends StudyRoomBaseClient {

    /**
     * 套餐列表（旧版，按区域分组）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/buy/package/list", timeout = 15000, readTimeout = 15000)
    StudyRoomApiResult<StudyRoomBuyPackageListResp> buyPackageList();

    /**
     * 套餐列表（v2，按门店/区域/类型筛选）
     * <p>
     * 入参实测为 {@code {packageType, packageLabelIds, venueId, seatAreaId}}
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/buy/v2/package/list")
    StudyRoomApiResult<StudyRoomBuyV2PackageListResp> buyV2PackageList(
        @JSONBody StudyRoomBuyV2PackageListReq form);

    /**
     * 套餐筛选项（门店、类型、标签）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @Get(url = "/mp/buy/v2/package/optList")
    StudyRoomApiResult<StudyRoomBuyV2PackageOptResp> buyV2PackageOptList(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat);

    /**
     * 套餐标签 / 座位区域筛选项
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/buy/v2/package/label/selectList")
    StudyRoomApiResult<StudyRoomBuyV2PackageLabelResp> buyV2PackageLabelList(
        @Query(value = "venueId") Integer venueId);

    /**
     * 套餐详情（v2）
     *
     * @param packageId 套餐 ID
     * @return 响应数据
     */
    @Get(url = "/mp/buy/v2/package/detail/${packageId}")
    StudyRoomApiResult<StudyRoomBuyV2PackageDetailResp> buyV2PackageDetail(
        @Var(value = "packageId") Long packageId);

    /**
     * 确认下单（旧版）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/buy/package/confirm")
    StudyRoomApiResult<StudyRoomBuyOrderResp> buyPackageConfirm(
        @JSONBody StudyRoomBuyPackageConfirmReq form);

    /**
     * 确认下单（v2）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/buy/v2/package/confirm")
    StudyRoomApiResult<StudyRoomBuyOrderResp> buyV2PackageConfirm(
        @JSONBody StudyRoomBuyV2PackageConfirmReq form);

    /**
     * 微信支付下单（返回小程序 wx.requestPayment 所需参数）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/buy/package/payOrder")
    StudyRoomApiResult<StudyRoomWxPayParamResp> buyPackagePayOrder(
        @JSONBody StudyRoomBuyPayOrderReq form);

    /**
     * 我的订单
     *
     * @return 响应数据
     */
    @Get(url = "/mp/user/userOrder/list")
    StudyRoomApiResult<StudyRoomUserOrderListResp> userOrderList();

    /**
     * 我的套餐
     *
     * @return 响应数据
     */
    @Get(url = "/mp/user/userPackage/list")
    StudyRoomApiResult<StudyRoomUserPackageListResp> userPackageList();

    /**
     * 微信支付状态查询（按支付单ID）
     * <p>
     * 支付完成后轮询该接口确认是否到账；{@code status=-10} 表示查不到该支付单。
     *
     * @param payId 支付单 ID
     * @return 响应数据
     */
    @Post(url = "/mp/wx/payment/v2/wx/query/status/{payId}")
    StudyRoomApiResult<StudyRoomWxPayQueryResp> wxPayQueryStatus(
        @Var(value = "payId") Long payId);

}

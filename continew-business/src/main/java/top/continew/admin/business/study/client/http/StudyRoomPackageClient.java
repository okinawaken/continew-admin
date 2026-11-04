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

package top.continew.admin.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Var;
import top.continew.admin.business.study.client.model.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.StudyRoomBuyOrderRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomBuyPackageListRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomBuyV2PackageDetailRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomBuyV2PackageLabelRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomBuyV2PackageListRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomBuyV2PackageOptRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserOrderListRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserPackageListRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomWxPayParamRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomWxPayQueryRespDTO;
import top.continew.admin.business.study.model.req.StudyRoomBuyPackageConfirmReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomBuyPayOrderReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomBuyV2PackageConfirmReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomBuyV2PackageListReqDTO;

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
    StudyRoomApiResultDTO<StudyRoomBuyPackageListRespDTO> buyPackageList();

    /**
     * 套餐列表（v2，按门店/区域/类型筛选）
     * <p>
     * 入参实测为 {@code {packageType, packageLabelIds, venueId, seatAreaId}}
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/buy/v2/package/list")
    StudyRoomApiResultDTO<StudyRoomBuyV2PackageListRespDTO> buyV2PackageList(
        @JSONBody StudyRoomBuyV2PackageListReqDTO form);

    /**
     * 套餐筛选项（门店、类型、标签）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @Get(url = "/mp/buy/v2/package/optList")
    StudyRoomApiResultDTO<StudyRoomBuyV2PackageOptRespDTO> buyV2PackageOptList(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat);

    /**
     * 套餐标签 / 座位区域筛选项
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/buy/v2/package/label/selectList")
    StudyRoomApiResultDTO<StudyRoomBuyV2PackageLabelRespDTO> buyV2PackageLabelList(
        @Query(value = "venueId") Integer venueId);

    /**
     * 套餐详情（v2）
     *
     * @param packageId 套餐 ID
     * @return 响应数据
     */
    @Get(url = "/mp/buy/v2/package/detail/${packageId}")
    StudyRoomApiResultDTO<StudyRoomBuyV2PackageDetailRespDTO> buyV2PackageDetail(
        @Var(value = "packageId") Long packageId);

    /**
     * 确认下单（旧版）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/buy/package/confirm")
    StudyRoomApiResultDTO<StudyRoomBuyOrderRespDTO> buyPackageConfirm(
        @JSONBody StudyRoomBuyPackageConfirmReqDTO form);

    /**
     * 确认下单（v2）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/buy/v2/package/confirm")
    StudyRoomApiResultDTO<StudyRoomBuyOrderRespDTO> buyV2PackageConfirm(
        @JSONBody StudyRoomBuyV2PackageConfirmReqDTO form);

    /**
     * 微信支付下单（返回小程序 wx.requestPayment 所需参数）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/buy/package/payOrder")
    StudyRoomApiResultDTO<StudyRoomWxPayParamRespDTO> buyPackagePayOrder(
        @JSONBody StudyRoomBuyPayOrderReqDTO form);

    /**
     * 我的订单
     *
     * @return 响应数据
     */
    @Get(url = "/mp/user/userOrder/list")
    StudyRoomApiResultDTO<StudyRoomUserOrderListRespDTO> userOrderList();

    /**
     * 我的套餐
     *
     * @return 响应数据
     */
    @Get(url = "/mp/user/userPackage/list")
    StudyRoomApiResultDTO<StudyRoomUserPackageListRespDTO> userPackageList();

    /**
     * 微信支付状态查询（按支付单ID）
     * <p>
     * 支付完成后轮询该接口确认是否到账；{@code status=-10} 表示查不到该支付单。
     *
     * @param payId 支付单 ID
     * @return 响应数据
     */
    @Post(url = "/mp/wx/payment/v2/wx/query/status/{payId}")
    StudyRoomApiResultDTO<StudyRoomWxPayQueryRespDTO> wxPayQueryStatus(
        @Var(value = "payId") Long payId);

}

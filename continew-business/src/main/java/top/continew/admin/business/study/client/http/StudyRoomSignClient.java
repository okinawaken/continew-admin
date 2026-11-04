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
import top.continew.admin.business.study.client.model.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.StudyRoomChangeSeatConfirmRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomIndexRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomRankRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomSeatConfirmRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomSettlePackageRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomSettlementRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomSignAnotherCheckRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomSignOutRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomSignSeatListRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomSignSeatMapRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserPackageConfirmRespDTO;
import top.continew.admin.business.study.model.req.StudyRoomChangeSeatConfirmReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomChangeSeatReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomEspLightTurnReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomRankReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomSeatConfirmReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomSettlePackageReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomSettlementReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomSignOutReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomSignSeatQueryReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomUserPackageConfirmReqDTO;

import java.util.List;

/**
 * 签到域：签到、换座、签退、排行榜
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomSignClient extends StudyRoomBaseClient {

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
    @Get(url = "/mp/study/index")
    StudyRoomApiResultDTO<StudyRoomIndexRespDTO> studyIndex(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat, @Query(value = "venueId") Integer venueId,
        @Query(value = "bssid") String bssid,
        @Query(value = "ssid") String ssid);

    /**
     * 签到选座
     * <p>
     * 该接口有两种响应：普通用户返回座位图（{@code code=200}）；存在待签到预约的用户由上游直接完成签到，
     * 返回 {@code code=SV0001} + 「预约座位XXX签到成功！」文案（{@code data=1}）。
     * 两种形态由 {@link StudyRoomSignSeatListRespDTO} 统一承接。
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/seat/list")
    StudyRoomApiResultDTO<StudyRoomSignSeatListRespDTO> studySeatList(
        @JSONBody StudyRoomSignSeatQueryReqDTO form);

    /**
     * 换座座位图
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/change/seat/list")
    StudyRoomApiResultDTO<StudyRoomSignSeatMapRespDTO> studyChangeSeatList(
        @JSONBody StudyRoomChangeSeatReqDTO form);

    /**
     * 签退可选结算套餐（实测 {@code data} 是数组）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/settlePackage/select")
    StudyRoomApiResultDTO<List<StudyRoomSettlePackageRespDTO>> studySettlePackageSelect(
        @JSONBody StudyRoomSettlePackageReqDTO form);

    /**
     * 是否可再签一个座位
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/study/signAnother/check")
    StudyRoomApiResultDTO<StudyRoomSignAnotherCheckRespDTO> studySignAnotherCheck(
        @Query(value = "venueId") Integer venueId);

    /**
     * 签退（扫码签退），返回结算结果
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/signOut")
    StudyRoomApiResultDTO<StudyRoomSignOutRespDTO> studySignOut(
        @JSONBody StudyRoomSignOutReqDTO form);

    /**
     * 学习排行榜
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/rank/index")
    StudyRoomApiResultDTO<StudyRoomRankRespDTO> studyRank(@JSONBody StudyRoomRankReqDTO form);

    /**
     * 签到选座确认（真正入座）
     * <p>
     * 与 {@link #studySeatList} 配对使用：先取座位图选座，再调本接口落座。
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/seat/confirm")
    StudyRoomApiResultDTO<StudyRoomSeatConfirmRespDTO> studySeatConfirm(
        @JSONBody StudyRoomSeatConfirmReqDTO form);

    /**
     * 换座确认
     * <p>
     * 与 {@link #studyChangeSeatList} 配对使用。未签到时调用会返回「还未签到，不能换座」。
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/change/seat/confirm")
    StudyRoomApiResultDTO<StudyRoomChangeSeatConfirmRespDTO> studyChangeSeatConfirm(
        @JSONBody StudyRoomChangeSeatConfirmReqDTO form);

    /**
     * 进入签退结算：返回结算明细 + 可选结算套餐
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/settlement")
    StudyRoomApiResultDTO<StudyRoomSettlementRespDTO> studySettlement(
        @JSONBody StudyRoomSettlementReqDTO form);

    /**
     * 确认签退结算套餐（选套餐扣费）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/study/userPackage/confirm")
    StudyRoomApiResultDTO<StudyRoomUserPackageConfirmRespDTO> studyUserPackageConfirm(
        @JSONBody StudyRoomUserPackageConfirmReqDTO form);

    /**
     * 座位灯控（开灯 / 关灯）
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/espLight/turn")
    StudyRoomApiResultDTO<Void> espLightTurn(@JSONBody StudyRoomEspLightTurnReqDTO form);

}

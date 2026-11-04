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
import top.continew.admin.business.study.client.model.StudyRoomReserveAnotherCheckRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomReserveCheckRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomReserveConfirmRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomReserveDateRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomSeatMapRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserReserveCancelTipRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserReserveListRespDTO;
import top.continew.admin.business.study.model.req.StudyRoomReserveConfirmReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomReserveConfirmV1ReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomSeatQueryReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomSeatQueryV1ReqDTO;
import top.continew.admin.business.study.model.req.StudyRoomUserReserveCancelReqDTO;

/**
 * 预约域：座位图、下单、我的预约、取消
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomReserveClient extends StudyRoomBaseClient {

    /**
     * 预约资格校验（是否有可预约套餐）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/check")
    StudyRoomApiResultDTO<StudyRoomReserveCheckRespDTO> reserveCheck();

    /**
     * 可预约日期列表（默认返回今天起 N 天，N 由 sysConfig.reserveMaxDays 决定）
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/v2/date/select")
    StudyRoomApiResultDTO<StudyRoomReserveDateRespDTO> reserveDateList(
        @Query(value = "venueId") Integer venueId);

    /**
     * 座位图（v2，推荐）：按日期 + 时间段查询门店全部座位
     * <p>
     * 时间段传空串表示查全天
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/v2/seat/list")
    StudyRoomApiResultDTO<StudyRoomSeatMapRespDTO> reserveV2SeatList(
        @JSONBody StudyRoomSeatQueryReqDTO form);

    /**
     * 提交预约（v2，推荐）
     * <p>
     * 实测返回 {@code data.id} 即 userReserveId，取消预约必须用它
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/v2/seat/confirm")
    StudyRoomApiResultDTO<StudyRoomReserveConfirmRespDTO> reserveV2SeatConfirm(
        @JSONBody StudyRoomReserveConfirmReqDTO form);

    /**
     * 座位图（v1，旧版）
     * <p>
     * 注意：v1 的时间参数格式是 {@code yyyy-MM-dd HH:mm}，不是 {@code HH:mm}
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/seat/list")
    StudyRoomApiResultDTO<StudyRoomSeatMapRespDTO> reserveSeatList(
        @JSONBody StudyRoomSeatQueryV1ReqDTO form);

    /**
     * 提交预约（v1，旧版）
     * <p>
     * 注意：v1 的时间参数格式是 {@code yyyy-MM-dd HH:mm}
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/reserve/seat/confirm")
    StudyRoomApiResultDTO<StudyRoomReserveConfirmRespDTO> reserveSeatConfirm(
        @JSONBody StudyRoomReserveConfirmV1ReqDTO form);

    /**
     * 是否还能再预约一个座位
     *
     * @param userReserveId 用户预约 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/another/check")
    StudyRoomApiResultDTO<StudyRoomReserveAnotherCheckRespDTO> reserveAnotherCheck(
        @Query(value = "userReserveId") Long userReserveId);

    /**
     * 我的预约列表（一次性返回全部，无分页）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/user/userReserve/list")
    StudyRoomApiResultDTO<StudyRoomUserReserveListRespDTO> userReserveList();

    /**
     * 取消预约前的提示（实测返回 {@code {tipDesc, userReserveId}}）
     *
     * @param userReserveId 用户预约 ID
     * @return 响应数据
     */
    @Get(url = "/mp/user/userReserve/cancel/tip")
    StudyRoomApiResultDTO<StudyRoomUserReserveCancelTipRespDTO> userReserveCancelTip(
        @Query(value = "userReserveId") Long userReserveId);

    /**
     * 取消预约
     * <p>
     * 实测返回 {@code data=1}（整数），不是对象
     *
     * @param form 请求参数
     * @return 响应数据
     */
    @Post(url = "/mp/user/userReserve/cancel")
    StudyRoomApiResultDTO<Integer> userReserveCancel(
        @JSONBody StudyRoomUserReserveCancelReqDTO form);

}

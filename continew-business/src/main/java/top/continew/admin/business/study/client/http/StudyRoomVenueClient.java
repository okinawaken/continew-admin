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
import com.dtflys.forest.annotation.Query;
import top.continew.admin.business.study.client.model.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.StudyRoomAreaSelectRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomDouYinVenueSelectRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomReserveIndexRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomVenueRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomVenueSelectRespDTO;

/**
 * 门店域：门店列表、门店详情、区域
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomVenueClient extends StudyRoomBaseClient {

    /**
     * 按定位查询附近门店（签到首页、报修页用）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @Get(url = "/mp/venue/select")
    StudyRoomApiResultDTO<StudyRoomVenueSelectRespDTO> venueSelect(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat);

    /**
     * 预约可选门店列表（无定位参数）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/venue/select")
    StudyRoomApiResultDTO<StudyRoomVenueSelectRespDTO> reserveVenueSelect();

    /**
     * 门店详情
     *
     * @param venueId 门店 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/venue/detail")
    StudyRoomApiResultDTO<StudyRoomVenueRespDTO> reserveVenueDetail(
        @Query(value = "venueId") Integer venueId);

    /**
     * 预约首页（banner + 门店列表 + 区域筛选）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @param venueName 门店名称
     * @param channelBili 渠道标识
     * @param areaId 区域 ID
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/index")
    StudyRoomApiResultDTO<StudyRoomReserveIndexRespDTO> reserveIndex(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat,
        @Query(value = "venueName") String venueName,
        @Query(value = "channelBili") Integer channelBili, @Query(value = "areaId") Long areaId);

    /**
     * 区域列表（预约首页筛选）
     *
     * @return 响应数据
     */
    @Get(url = "/mp/reserve/area/select")
    StudyRoomApiResultDTO<StudyRoomAreaSelectRespDTO> reserveAreaSelect();

    /**
     * 收银台门店列表（美团团购核销）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @Get(url = "/mp/receipt/venue/select")
    StudyRoomApiResultDTO<StudyRoomVenueSelectRespDTO> receiptVenueSelect(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat);

    /**
     * 抖音门店列表（比普通门店多 poiId / poiName）
     *
     * @param currentLng 当前经度
     * @param currentLat 当前纬度
     * @return 响应数据
     */
    @Get(url = "/mp/douYin/venue/select")
    StudyRoomApiResultDTO<StudyRoomDouYinVenueSelectRespDTO> douYinVenueSelect(
        @Query(value = "currentLng") Double currentLng,
        @Query(value = "currentLat") Double currentLat);

}

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

package top.continew.admin.business.study.filter.autosign.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.StudyRoomVenueV2Client;
import top.continew.admin.business.study.client.model.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.StudyRoomUserReserveRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomVenueRespDTO;
import top.continew.admin.business.study.enums.StudyRoomAutoSignFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autosign.StudyRoomAutoSignFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoSignContext;

import java.util.Objects;

/**
 * 签到门店校验过滤器
 * <p>
 * 按待签到预约的门店 ID 查询门店详情，校验门店存在并写入执行上下文，
 * 后续签到首页使用该门店的经纬度作为当前定位。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignVenueFilter implements StudyRoomAutoSignFilter {

    private final StudyRoomVenueV2Client venueV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoSignFilterOrderEnum.VENUE.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context) {
        StudyRoomUserReserveRespDTO reserveRespDTO = context.getReserveRespDTO();
        StudyRoomApiResultDTO<StudyRoomVenueRespDTO> resultDTO =
            venueV2Client.reserveVenueDetail(reserveRespDTO.getVenueId(), context.getUserId());
        if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData())) {
            return FilterResultDTO.fail("未查询到门店数据");
        }

        StudyRoomVenueRespDTO venueRespDTO = resultDTO.getData();
        context.setVenueRespDTO(venueRespDTO);
        return FilterResultDTO.success(venueRespDTO);
    }
}

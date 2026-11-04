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

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.StudyRoomReserveV2Client;
import top.continew.admin.business.study.client.model.resp.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserReserveListRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomUserReserveRespDTO;
import top.continew.admin.business.study.enums.StudyRoomAutoSignFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autosign.StudyRoomAutoSignFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoSignContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * 待签到预约查询过滤器
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignPendingReserveFilter implements StudyRoomAutoSignFilter {

    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoSignFilterOrderEnum.PENDING_RESERVE.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context) {
        StudyRoomUserReserveRespDTO reserveRespDTO = this.findPendingReserve(context.getUserId());
        context.setReserveRespDTO(reserveRespDTO);
        if (Objects.isNull(reserveRespDTO)) {
            return FilterResultDTO.fail("当前无待签到预约");
        }
        return FilterResultDTO.success(reserveRespDTO);
    }

    private StudyRoomUserReserveRespDTO findPendingReserve(Long userId) {
        StudyRoomApiResultDTO<StudyRoomUserReserveListRespDTO> resultDTO =
            reserveV2Client.userReserveList(userId);
        return resultDTO.getData().getList().stream()
            .filter(reserve -> StrUtil.equals(reserve.getReserveStatusDesc(), "待签到"))
            .filter(reserve -> Objects.equals(reserve.getReserveDateStr(), LocalDate.now()))
            .filter(reserve -> {
                LocalTime currentTime = LocalTime.now();
                String[] reserveTimeStrArray =
                    StrUtil.splitToArray(reserve.getReserveTimeStr(), '-');
                LocalTime beginTime = LocalTime.parse(reserveTimeStrArray[0]);
                LocalTime endTime = LocalTime.parse(reserveTimeStrArray[1]);
                return !currentTime.isBefore(beginTime) && !currentTime.isAfter(endTime);
            })
            .findFirst()
            .orElse(null);
    }
}

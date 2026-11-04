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

package top.continew.admin.business.study.filter.autoreserve.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.StudyRoomReserveV2Client;
import top.continew.admin.business.study.client.model.req.StudyRoomSeatQueryReqDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSeatAreaRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSeatMapRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSeatRespDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomSeatValidTimeRespDTO;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.enums.StudyRoomSeatStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRespDTO;
import top.continew.starter.core.exception.BusinessException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * 座位图查询与选座过滤器
 * <p>
 * 选座优先级：指定座位 → 偏好区域首个可约座位 → 全场首个可约座位 → 失败。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveSeatFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.SEAT.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        LocalDate targetDate = context.getTargetDate();

        StudyRoomSeatQueryReqDTO reqDTO = new StudyRoomSeatQueryReqDTO();
        reqDTO.setVenueId(taskRespDTO.getVenueId());
        reqDTO.setReserveDateStr(targetDate);
        reqDTO.setReserveStartTimeStr(taskRespDTO.getReserveStartTime());
        reqDTO.setReserveEndTimeStr(taskRespDTO.getReserveEndTime());
        reqDTO.setReserveAnotherFlag(0);
        StudyRoomApiResultDTO<StudyRoomSeatMapRespDTO> resultDTO =
            reserveV2Client.reserveV2SeatList(reqDTO, taskRespDTO.getUserId());
        if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData())) {
            throw new BusinessException("座位图返回为空");
        }

        StudyRoomSeatRespDTO seatRespDTO = this.selectSeat(context, resultDTO.getData());
        context.setSeatRespDTO(seatRespDTO);
        if (Objects.isNull(seatRespDTO)) {
            return FilterResultDTO.fail("期望时段无可预约座位");
        }
        return FilterResultDTO.success(seatRespDTO);
    }

    private StudyRoomSeatRespDTO selectSeat(StudyRoomAutoReserveContext context,
        StudyRoomSeatMapRespDTO seatMap) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        StudyRoomSeatRespDTO areaSeatRespDTO = null;
        StudyRoomSeatRespDTO anySeatRespDTO = null;

        for (StudyRoomSeatAreaRespDTO seatAreaRespDTO : seatMap.getVenueAreaList()) {
            boolean isPreferredArea =
                Objects.equals(seatAreaRespDTO.getId(), taskRespDTO.getAreaId());
            for (StudyRoomSeatRespDTO seatRespDTO : seatAreaRespDTO.getVenueAreaSeatList()) {
                boolean seatAvailable = this.isSeatAvailable(seatRespDTO,
                    taskRespDTO.getReserveStartTime(), taskRespDTO.getReserveEndTime());
                if (!seatAvailable) {
                    continue;
                }
                if (Objects.equals(seatRespDTO.getId(), taskRespDTO.getSeatId())) {
                    return seatRespDTO;
                }
                if (Objects.isNull(areaSeatRespDTO) && isPreferredArea) {
                    areaSeatRespDTO = seatRespDTO;
                }
                if (Objects.isNull(anySeatRespDTO)) {
                    anySeatRespDTO = seatRespDTO;
                }
            }
        }
        return Objects.nonNull(areaSeatRespDTO) ? areaSeatRespDTO : anySeatRespDTO;
    }

    private boolean isSeatAvailable(StudyRoomSeatRespDTO seat, LocalTime startTime,
        LocalTime endTime) {
        if (!StudyRoomSeatStatusEnum.isCanReserve(seat.getSeatReserveState())) {
            return false;
        }

        for (StudyRoomSeatValidTimeRespDTO validTime : seat.getValidTimeStrList()) {
            if (!startTime.isBefore(validTime.getValidStartTimeStr())
                && !endTime.isAfter(validTime.getValidEndTimeStr())) {
                return true;
            }
        }
        return false;
    }
}

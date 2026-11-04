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
import top.continew.admin.business.study.client.http.StudyRoomVenueV2Client;
import top.continew.admin.business.study.client.model.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.StudyRoomVenueRespDTO;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.enums.StudyRoomReserveStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRespDTO;
import top.continew.starter.core.exception.BusinessException;

import java.util.Objects;

/**
 * 预约门店校验过滤器
 * <p>
 * 查询门店详情并校验门店是否可预约，结果缓存到执行上下文。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveVenueFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomVenueV2Client studyRoomVenueV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.VENUE.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        StudyRoomApiResultDTO<StudyRoomVenueRespDTO> resultDTO = studyRoomVenueV2Client
            .reserveVenueDetail(taskRespDTO.getVenueId(), taskRespDTO.getUserId());
        if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData())) {
            throw new BusinessException("未查询到门店数据");
        }
        StudyRoomVenueRespDTO venueRespDTO = resultDTO.getData();
        context.setVenueRespDTO(venueRespDTO);
        if (!StudyRoomReserveStatusEnum.isCanReserve(venueRespDTO.getVenueReserveState())) {
            return FilterResultDTO.fail("门店当前不可预约");
        }
        return FilterResultDTO.success(venueRespDTO);
    }
}

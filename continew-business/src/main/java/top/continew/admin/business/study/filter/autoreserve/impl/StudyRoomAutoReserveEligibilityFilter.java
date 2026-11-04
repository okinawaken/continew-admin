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
import top.continew.admin.business.study.client.model.resp.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.resp.StudyRoomReserveCheckRespDTO;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.enums.StudyRoomReserveStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRespDTO;

/**
 * 预约资格校验过滤器
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveEligibilityFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        StudyRoomApiResultDTO<StudyRoomReserveCheckRespDTO> resultDTO =
            reserveV2Client.reserveCheck(taskRespDTO.getUserId());
        StudyRoomReserveCheckRespDTO reserveCheckRespDTO = resultDTO.getData();

        if (!StudyRoomReserveStatusEnum.isCanReserve(reserveCheckRespDTO.getReserveState())) {
            return FilterResultDTO.fail("无预约资格");
        }
        return FilterResultDTO.success(reserveCheckRespDTO);
    }

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.ELIGIBILITY.getValue();
    }
}

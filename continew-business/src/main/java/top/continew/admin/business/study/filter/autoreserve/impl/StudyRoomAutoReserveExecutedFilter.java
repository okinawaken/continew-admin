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

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRecordRespDTO;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRespDTO;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;

import java.time.LocalDate;
import java.util.List;

/**
 * 今日已执行过滤器
 * <p>
 * 当天已有执行记录时直接终止链路，且不写新的执行记录。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveExecutedFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomAutoReserveTaskRecordService recordService;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.EXECUTED.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        List<StudyRoomAutoReserveTaskRecordRespDTO> recordList =
            recordService.listByTaskIdAndCreateDate(taskRespDTO.getId(), LocalDate.now());
        if (CollUtil.isNotEmpty(recordList)) {
            return FilterResultDTO.fail("今日已执行");
        }
        return FilterResultDTO.success(recordList);
    }
}

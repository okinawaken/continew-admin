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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRespDTO;

import java.time.LocalDate;

/**
 * 执行星期过滤器
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Slf4j
@Component
public class StudyRoomAutoReserveWeekdayFilter implements StudyRoomAutoReserveFilter {

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.WEEKDAY.getValue();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        LocalDate targetDate = context.getTargetDate();
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        if (!CollUtil.contains(taskRespDTO.getWeekDays(), targetDate.getDayOfWeek().getValue())) {
            return FilterResultDTO.fail("目标日期不在执行计划内");
        }
        return FilterResultDTO.success(targetDate);
    }
}

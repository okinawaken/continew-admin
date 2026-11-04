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
import top.continew.admin.business.study.client.http.StudyRoomReserveV2Client;
import top.continew.admin.business.study.client.model.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.StudyRoomReserveDateRespDTO;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRespDTO;
import top.continew.starter.core.exception.BusinessException;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 目标日期与放号基线过滤器
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveTargetDateFilter implements StudyRoomAutoReserveFilter {

    private static final Pattern DATE_PREFIX_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}");

    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.TARGET_DATE.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        StudyRoomApiResultDTO<StudyRoomReserveDateRespDTO> resultDTO =
            reserveV2Client.reserveDateList(taskRespDTO.getVenueId(), taskRespDTO.getUserId());
        StudyRoomReserveDateRespDTO reserveDateRespDTO = resultDTO.getData();

        LocalDate targetDate = this.resolveMaxReserveDate(reserveDateRespDTO);
        context.setTargetDate(targetDate);

        if (!targetDate.isAfter(taskRespDTO.getLastMaxReserveDate())) {
            return FilterResultDTO.fail("未放出新日期的预约");
        }
        return FilterResultDTO.success(targetDate);
    }

    private LocalDate resolveMaxReserveDate(StudyRoomReserveDateRespDTO dateData) {
        if (CollUtil.isEmpty(dateData.getReserveDateList())) {
            throw new BusinessException("日期字符串返回为空");
        }
        LocalDate maxDate = null;
        for (String rawDate : dateData.getReserveDateList()) {
            Matcher matcher = DATE_PREFIX_PATTERN.matcher(rawDate);
            if (!matcher.find()) {
                throw new BusinessException("解析日期字符串失败");
            }
            LocalDate date = LocalDate.parse(matcher.group());
            if (Objects.isNull(maxDate) || date.isAfter(maxDate)) {
                maxDate = date;
            }
        }
        return maxDate;
    }
}

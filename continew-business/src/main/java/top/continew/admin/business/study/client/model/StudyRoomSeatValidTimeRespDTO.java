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

package top.continew.admin.business.study.client.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalTime;

/**
 * 座位可预约时段（StudyRoomSeatRespDTO.validTimeStrList 元素）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSeatValidTimeRespDTO {

    /**
     * 可预约开始时间 HH:mm
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime validStartTimeStr;

    /**
     * 可预约结束时间 HH:mm
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime validEndTimeStr;

    /**
     * 可预约时长描述，如 3小时50分钟
     */
    private String validMinuteStr;

    /**
     * 可预约时段，如 06:00-09:50
     */
    private String validTimeStr;
}

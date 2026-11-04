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

package top.continew.admin.business.study.model.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 座位查询（/mp/reserve/v2/seat/list）
 * <p>
 * 实测：时间可以不传（传空串），此时返回当天全部座位状态。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomSeatQueryReqDTO {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 预约日期 yyyy-MM-dd（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDateStr;

    /**
     * 开始时间 HH:mm，可空
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveStartTimeStr;

    /**
     * 结束时间 HH:mm，可空
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveEndTimeStr;

    /**
     * 是否再约一个座位：1 是 0 否
     */
    private Integer reserveAnotherFlag;
}

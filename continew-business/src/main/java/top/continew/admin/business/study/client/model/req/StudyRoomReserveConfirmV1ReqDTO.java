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

package top.continew.admin.business.study.client.model.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * v1 提交预约（/mp/reserve/seat/confirm）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomReserveConfirmV1ReqDTO {

    /**
     * 座位ID（必填）
     */
    private Long seatId;

    /**
     * 预约开始时间，格式必须是 yyyy-MM-dd HH:mm（实测传 HH:mm 会报 Parse error）（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reserveStartTime;

    /**
     * 结束时间，格式必须是 yyyy-MM-dd HH:mm（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reserveEndTime;
}

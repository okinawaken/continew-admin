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

package top.continew.admin.business.study.model.context;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import top.continew.admin.business.study.client.model.StudyRoomReserveConfirmRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomSeatRespDTO;
import top.continew.admin.business.study.client.model.StudyRoomVenueRespDTO;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRespDTO;

import java.time.LocalDate;

/**
 * 自动预约执行上下文
 * <p>
 * 在过滤器链中传递任务、目标日期、座位与终态信息。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@RequiredArgsConstructor
public class StudyRoomAutoReserveContext {

    /**
     * 任务数据
     */
    private final StudyRoomAutoReserveTaskRespDTO taskRespDTO;

    /**
     * 触发方式
     */
    private final Integer triggerType;

    /**
     * 目标预约日期
     */
    private LocalDate targetDate;

    /**
     * 选中的门店
     */
    private StudyRoomVenueRespDTO venueRespDTO;

    /**
     * 选中的座位
     */
    private StudyRoomSeatRespDTO seatRespDTO;

    /**
     * 预约确认结果
     */
    private StudyRoomReserveConfirmRespDTO confirmRespDTO;
}

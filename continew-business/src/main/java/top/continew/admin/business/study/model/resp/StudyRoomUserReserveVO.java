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

package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * StudyRoomUserReserveRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserReserveVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 预约ID
     */
    private Long id;

    /**
     * 门店ID
     */
    private Integer venueId;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 预约日期 yyyy-MM-dd
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDateStr;

    /**
     * 预约时段，如 09:00-10:00
     */
    private String reserveTimeStr;

    /**
     * 星期，如 周三
     */
    private String reserveWeekStr;

    /**
     * 预约状态描述，如 已取消 / 待签到 / 已完成
     */
    private String reserveStatusDesc;

    /**
     * 是否可取消：1 可取消
     */
    private Integer canCancel;

    /**
     * 是否可再约一个座位：1 可以
     */
    private Integer canReserveAnother;

    /**
     * 不可再约的原因
     */
    private String descNoReserveAnother;

    /**
     * 是否订阅提醒
     */
    private Integer subscribeFlag;
}

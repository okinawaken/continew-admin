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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * StudyRoomUserSignRespDTO 视图对象
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserSignVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 签到ID（签退、换座时作为 userSignId）
     */
    private Long id;

    /**
     * 今日第几位打卡，0 表示不展示
     */
    private Integer signSerialNo;

    /**
     * 学习日期，上游返回 yyyy.MM.dd（仅历史记录列表返回）
     */
    private String signDateStr;

    /**
     * 门店（仅历史记录列表返回）
     */
    private StudyRoomVenueSimpleVO venue;

    /**
     * 门店ID（上游未直接返回时从 venue.id 派生）
     */
    private Integer venueId;

    /**
     * 签到时间；在座信息里是 yyyy-MM-dd HH:mm:ss，历史记录列表里是 HH:mm
     */
    private String signInTimeStr;

    /**
     * 预计自动签退时间（仅当前在座信息返回）
     */
    private String signOutExpectTimeStr;

    /**
     * 实际签退时间；历史记录列表里是 HH:mm
     */
    private String signOutTimeStr;

    /**
     * 结算套餐名称（当前在座信息）
     */
    private String packageName;

    /**
     * 套餐剩余（当前在座信息）
     */
    private String restTimeStr;

    /**
     * 使用时段（当前在座信息）
     */
    private String settleHourStr;

    /**
     * 可用区域描述（当前在座信息）
     */
    private String areaDesc;

    /**
     * 是否已扣费：1 已扣（仅历史记录列表返回）
     */
    private Integer hasPayed;

    /**
     * 已扣费的套餐名称（仅历史记录列表返回）
     */
    private String packageNamePayed;

    /**
     * 区域与座位号（仅历史记录列表返回）
     */
    private StudyRoomVenueAreaSeatVO venueAreaSeat;

    /**
     * 学习时长文案（仅历史记录列表返回）
     */
    private String studyMinuteStr;

    /**
     * 座位信息（仅当前在座信息返回）
     */
    private SeatInfo seatInfo;

    public Integer getVenueId() {
        if (Objects.nonNull(venueId)) {
            return venueId;
        }
        if (Objects.nonNull(venue)) {
            return venue.getId();
        }
        return null;
    }

    /**
     * 签到座位信息
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SeatInfo implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * 区域名称
         */
        private String areaName;

        /**
         * 座位号
         */
        private String seatNumber;

        /**
         * 签到时间
         */
        private String signInTimeStr;

        /**
         * 已学习小时数
         */
        private Integer studyHours;

        /**
         * 已学习分钟数
         */
        private Integer studyMinutes;
    }
}

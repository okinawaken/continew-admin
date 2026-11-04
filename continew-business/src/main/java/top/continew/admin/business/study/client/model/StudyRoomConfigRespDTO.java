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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序全局配置（/mp/sys/config/data）
 * <p>
 * 只映射了与预约/签到相关的关键字段，其余字段忽略。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomConfigRespDTO {

    /**
     * 最多可提前预约天数
     */
    private Integer reserveMaxDays;

    /**
     * 单次预约最少小时数
     */
    private Integer reserveMinHours;

    /**
     * 预约后保留座位分钟数
     */
    private Integer reserveHoldMinutes;

    /**
     * 预约背景图
     */
    private String reserveBackImg;

    /**
     * 预约成功提示语
     */
    private String reserveSuccessTip;

    /**
     * 学习首页背景图
     */
    private String studyBackImg;

    /**
     * 黑名单用户提示
     */
    private String blackUserTipText;

    /**
     * 请假提示
     */
    private String leaveTipText;

    /**
     * 是否支持多座位签到：1 支持
     */
    private Integer moreUserSignListOpen;

    /**
     * 区域封面类型开关（字符串 true/false）
     */
    private String reserveAreaCoverType;

    /**
     * 储物柜入口开关
     */
    @JsonProperty(value = "cabinetPlusShow")
    private Boolean cabinetPlusShowFlag;

    /**
     * 是否开启抖音券核销（字符串 true/false）
     */
    private String douYinOpen;

    /**
     * 是否开启美团券核销（字符串 true/false）
     */
    private String meiTuanOpen;
}

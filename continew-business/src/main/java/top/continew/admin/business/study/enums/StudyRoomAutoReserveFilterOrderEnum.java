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

package top.continew.admin.business.study.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.continew.starter.core.enums.BaseEnum;

/**
 * 自动预约过滤器执行顺序
 * <p>
 * 数值越小越先执行，供 {@code StudyRoomAutoReserveFilter} 实现类声明自身顺序。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomAutoReserveFilterOrderEnum implements BaseEnum<Integer> {

    /**
     * 启用状态校验
     */
    ENABLED(100, "启用状态校验"),

    /**
     * 当日已执行校验
     */
    EXECUTED(200, "当日已执行校验"),

    /**
     * 预约门店校验
     */
    VENUE(300, "预约门店校验"),

    /**
     * 目标日期计算
     */
    TARGET_DATE(400, "目标日期计算"),

    /**
     * 执行星期校验
     */
    WEEKDAY(500, "执行星期校验"),

    /**
     * 座位选择
     */
    SEAT(600, "座位选择"),

    /**
     * 预约资格校验
     */
    ELIGIBILITY(700, "预约资格校验");

    /**
     * 枚举值，值越小越先执行
     */
    private final Integer value;

    /**
     * 枚举描述
     */
    private final String description;
}

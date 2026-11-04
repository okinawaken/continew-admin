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

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自动签到过滤器执行顺序
 * <p>
 * 数值越小越先执行，供 {@code StudyRoomAutoSignFilter} 实现类声明自身顺序。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Getter
@AllArgsConstructor
public enum StudyRoomAutoSignFilterOrderEnum {

    /**
     * 待签到预约查询
     */
    PENDING_RESERVE(100, "待签到预约查询"),

    /**
     * 签到门店校验
     */
    VENUE(200, "签到门店校验"),

    /**
     * 签到扣费套餐查询
     */
    SIGN_IN_PACKAGE(300, "签到扣费套餐查询");

    /**
     * 执行顺序，值越小越先执行
     */
    private final int order;

    /**
     * 说明
     */
    private final String desc;
}

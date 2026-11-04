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

import java.util.Objects;

/**
 * 座位可预约状态（seat/list 返回的 seatReserveState）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Getter
@AllArgsConstructor
public enum StudyRoomSeatStatusEnum {

    /**
     * 10 空闲
     */
    FREE(10, "空闲"),

    /**
     * 20 部分时段已被预约
     */
    PART(20, "部分时段已被预约"),

    /**
     * 30 不可预约（vip / 未开放 / 已约满）
     */
    DISABLED(30, "不可预约");

    private final Integer value;

    private final String desc;

    /**
     * 是否可预约（空闲、部分时段可约均可预约）
     *
     * @param value 枚举值
     * @return 是否可预约
     */
    public static boolean isCanReserve(Integer value) {
        return Objects.equals(FREE.getValue(), value) || Objects.equals(PART.getValue(), value);
    }
}

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

import java.util.Objects;

/**
 * 预约状态（reserve/check 返回的 reserveState）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomReserveStatusEnum implements BaseEnum<Integer> {

    /**
     * 0 可预约
     */
    CAN(0, "可预约"),

    /**
     * 1 不可预约
     */
    CANNOT(1, "不可预约");

    private final Integer value;

    private final String description;

    /**
     * 是否可预约
     *
     * @param value 枚举值
     * @return 是否可预约
     */
    public static boolean isCanReserve(Integer value) {
        return Objects.equals(CAN.getValue(), value);
    }
}

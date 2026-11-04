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
 * 自动预约任务启用状态
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomAutoReserveTaskEnableStatusEnum implements BaseEnum<Integer> {

    /**
     * 暂停：不参与派发，配置保留
     */
    PAUSED(0, "暂停"),

    /**
     * 启用：参与扫描与派发
     */
    ENABLED(1, "启用");

    private final Integer value;

    private final String description;

    /**
     * 是否暂停
     *
     * @param value 枚举值
     * @return 是否暂停
     */
    public static boolean isPaused(Integer value) {
        return Objects.equals(PAUSED.getValue(), value);
    }

    /**
     * 是否启用
     *
     * @param value 枚举值
     * @return 是否启用
     */
    public static boolean isEnabled(Integer value) {
        return Objects.equals(ENABLED.getValue(), value);
    }
}

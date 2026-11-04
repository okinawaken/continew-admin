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
 * 自动签到开关
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Getter
@RequiredArgsConstructor
public enum StudyRoomAutoSignInEnableEnum implements BaseEnum<Integer> {

    /**
     * 关闭：不参与自动签到
     */
    DISABLED(0, "关闭"),

    /**
     * 开启：放号预约成功后自动签到
     */
    ENABLED(1, "开启");

    private final Integer value;

    private final String description;

    /**
     * 是否开启自动签到
     *
     * @param value 枚举值
     * @return 是否启用
     */
    public static boolean isEnabled(Integer value) {
        return Objects.equals(ENABLED.getValue(), value);
    }
}

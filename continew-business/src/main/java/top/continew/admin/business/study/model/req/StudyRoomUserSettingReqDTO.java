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

package top.continew.admin.business.study.model.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * 用户设置写请求
 * <p>
 * <b>整对象覆盖</b>：未传的配置项会回落代码默认值，不是局部更新。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserSettingReqDTO {

    /**
     * 放号时刻 {@code HH:mm[:ss]}
     */
    @NotNull(message = "放号时刻不能为空")
    @JsonFormat(pattern = "HH:mm[:ss]")
    private LocalTime releaseTime;

    /**
     * 是否启用自动签到：0 关闭，1 开启
     * <p>
     * 不传（null）时保留原配置值。
     */
    @Min(value = 0, message = "自动签到开关取值不合法")
    @Max(value = 1, message = "自动签到开关取值不合法")
    private Integer signInEnable;
}

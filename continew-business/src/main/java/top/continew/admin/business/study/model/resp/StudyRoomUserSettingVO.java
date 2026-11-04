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

import java.time.LocalTime;

/**
 * 用户设置
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserSettingVO {

    /**
     * 放号时刻 {@code HH:mm:ss}
     */
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime releaseTime;

    /**
     * 是否启用自动签到：0 关闭，1 开启
     */
    private Integer signInEnable;
}

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
import lombok.Data;

/**
 * 小程序统一响应体
 * <p>
 * 实际返回示例：
 *
 * <pre>
 * {"code":"200","content":"","data":{...},"subData":null}
 * {"code":"SV00000","content":"您还未签到入座!","data":null,"subData":null}
 * </pre>
 *
 * @author Amadeus
 * @param <T> 业务数据类型
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomApiResultDTO<T> {

    /**
     * 业务码，"200" 表示成功
     */
    private String code;

    /**
     * 提示文案，失败时展示给用户
     */
    private String content;

    /**
     * 业务数据
     */
    private T data;

    /**
     * 子数据（部分接口返回）
     */
    private Object subData;
}

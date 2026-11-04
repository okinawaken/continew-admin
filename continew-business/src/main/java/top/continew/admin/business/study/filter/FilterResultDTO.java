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

package top.continew.admin.business.study.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 过滤器执行结果，包含成功标识、数据和失败原因。
 *
 * @author Amadeus
 * @param <T> 结果数据类型
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterResultDTO<T> {

    private boolean success;

    private T data;

    private String errorMsg;

    /**
     * 构造成功结果
     *
     * @param <T> 泛型参数
     * @return 成功结果
     */
    public static <T> FilterResultDTO<T> success() {
        return success(null);
    }

    /**
     * 构造带数据的成功结果
     *
     * @param data 数据
     * @param <T> 泛型参数
     * @return 成功结果
     */
    public static <T> FilterResultDTO<T> success(T data) {
        FilterResultDTO<T> result = new FilterResultDTO<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    /**
     * 构造失败结果
     *
     * @param <T> 泛型参数
     * @param errorMsg 失败原因
     * @return 失败结果
     */
    public static <T> FilterResultDTO<T> fail(String errorMsg) {
        FilterResultDTO<T> result = new FilterResultDTO<>();
        result.setSuccess(false);
        result.setErrorMsg(errorMsg);
        return result;
    }
}

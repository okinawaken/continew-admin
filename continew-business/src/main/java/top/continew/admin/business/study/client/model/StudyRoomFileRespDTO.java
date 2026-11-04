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
 * 图片信息（门店图、banner、头像等）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomFileRespDTO {

    /**
     * 文件 key
     */
    private String fileKey;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 可直接访问的 URL
     */
    private String fileUrl;

    /**
     * 跳转类型：0 不跳转 1 跳 H5
     */
    private Integer linkHtmlType;

    /**
     * 跳转地址
     */
    private String linkHtmlUrl;
}

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
 * 登录 / 绑定手机号结果（/mp/user/login、/mp/user/authorize/bindMobile）
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomLoginRespDTO {

    /**
     * 小程序 token（JWT），后续所有请求放在 token 请求头
     */
    private String token;

    /**
     * 是否已绑定手机号：1 是 0 否
     */
    private Integer bindMobile;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 真实姓名
     */
    private String trueName;

    /**
     * 手机号
     */
    private String mobile;
}

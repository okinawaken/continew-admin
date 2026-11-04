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

package top.continew.admin.business.study.model.bo;

import lombok.Data;

/**
 * 用户配置扫描视图
 * <p>
 * 定时任务需要跨租户扫描全部配置，因此除用户 ID 与配置内容外，额外携带租户 ID，
 * 供异步执行时恢复租户上下文。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Data
public class StudyRoomUserConfigView {

    /**
     * 租户 ID
     */
    private Long tenantId;

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 自动预约用户配置
     */
    private StudyRoomUserConfigBO config;
}

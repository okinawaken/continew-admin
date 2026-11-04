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

package top.continew.admin.business.study.service.user;

import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigView;
import top.continew.starter.extension.tenant.annotation.TenantIgnore;

import java.util.List;

/**
 * 自习室用户设置服务
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomUserSettingService {

    /**
     * 读取用户配置，无记录时返回默认值
     *
     * @param userId 系统用户 ID
     * @return 详情数据
     */
    StudyRoomUserConfigBO getConfig(Long userId);

    /**
     * 读取全部用户配置（跨租户扫描）
     * <p>
     * 仅包含已落库的配置；未落库的用户不在结果中，其默认值请用 {@link #getConfig(Long)} 获取。
     *
     * @return 列表数据
     */
    @TenantIgnore
    List<StudyRoomUserConfigView> listAllConfigs();

    /**
     * 覆盖写用户配置
     *
     * @param userId 系统用户 ID
     * @param config 新配置，未传的项回落默认值
     */
    void updateConfig(Long userId, StudyRoomUserConfigBO config);
}

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

package top.continew.admin.business.study.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.business.study.enums.StudyRoomAutoSignInEnableEnum;
import top.continew.admin.business.study.mapper.StudyRoomUserSettingMapper;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigView;
import top.continew.admin.business.study.model.entity.StudyRoomUserSettingDO;
import top.continew.admin.business.study.service.user.StudyRoomUserSettingService;
import top.continew.starter.extension.tenant.annotation.TenantIgnore;
import top.continew.starter.json.jackson.util.JsonUtils;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * 自习室用户设置服务实现
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Service
@RequiredArgsConstructor
public class StudyRoomUserSettingServiceImpl implements StudyRoomUserSettingService {

    private final StudyRoomUserSettingMapper settingMapper;

    @Override
    public StudyRoomUserConfigBO getConfig(Long userId) {
        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserSettingDO::getUserId, userId);
        StudyRoomUserSettingDO entity = settingMapper.selectOne(lqw);
        if (Objects.isNull(entity)) {
            return this.defaultConfig();
        }
        StudyRoomUserConfigBO bo =
            JsonUtils.toBean(entity.getConfigJson(), StudyRoomUserConfigBO.class);
        if (Objects.isNull(bo)) {
            return this.defaultConfig();
        }
        return bo;
    }

    @Override
    @TenantIgnore
    public List<StudyRoomUserConfigView> listAllConfigs() {
        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        List<StudyRoomUserSettingDO> settingList = settingMapper.selectList(lqw);
        return settingList.stream().map(settingDO -> {
            StudyRoomUserConfigView view = new StudyRoomUserConfigView();
            view.setTenantId(settingDO.getTenantId());
            view.setUserId(settingDO.getUserId());
            StudyRoomUserConfigBO bo = JsonUtils
                .toBean(settingDO.getConfigJson(), StudyRoomUserConfigBO.class);
            view.setConfig(Objects.isNull(bo) ? this.defaultConfig() : bo);
            return view;
        }).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(Long userId, StudyRoomUserConfigBO config) {
        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserSettingDO::getUserId, userId);
        StudyRoomUserSettingDO exist = settingMapper.selectOne(lqw);
        if (Objects.nonNull(exist)) {
            exist.setConfigJson(JsonUtils.toJsonStr(config));
            settingMapper.updateById(exist);
        } else {
            StudyRoomUserSettingDO entity = new StudyRoomUserSettingDO();
            entity.setUserId(userId);
            entity.setConfigJson(JsonUtils.toJsonStr(config));
            settingMapper.insert(entity);
        }
    }

    private StudyRoomUserConfigBO defaultConfig() {
        StudyRoomUserConfigBO studyRoomAutoReserveUserConfigBO =
            new StudyRoomUserConfigBO();
        studyRoomAutoReserveUserConfigBO.setReleaseTime(LocalTime.of(20, 0, 0));
        studyRoomAutoReserveUserConfigBO
            .setSignInEnable(StudyRoomAutoSignInEnableEnum.DISABLED.getValue());
        return studyRoomAutoReserveUserConfigBO;
    }
}

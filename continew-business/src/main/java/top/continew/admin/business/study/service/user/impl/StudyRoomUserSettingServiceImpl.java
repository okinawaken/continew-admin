package top.continew.admin.business.study.service.user.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.business.study.client.http.StudyRoomUserClient;
import top.continew.admin.business.study.enums.StudyRoomAutoSignInEnableEnum;
import top.continew.admin.business.study.mapper.StudyRoomUserSettingMapper;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigView;
import top.continew.admin.business.study.model.entity.StudyRoomUserSettingDO;
import top.continew.admin.business.study.model.req.StudyRoomUserSettingReq;
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
    private final StudyRoomUserClient studyRoomUserClient;

    @Override
    public StudyRoomUserConfigBO getConfig(Long userId) {
        StudyRoomUserSettingDO entity = this.getEntity(userId);
        return Objects.isNull(entity) ? this.defaultConfig() : this
            .parseConfig(entity.getConfigJson());
    }

    @Override
    @TenantIgnore
    public List<StudyRoomUserConfigView> listAllConfigs() {
        List<StudyRoomUserSettingDO> settingList = settingMapper.selectList(Wrappers
            .lambdaQuery());
        return settingList.stream().map(settingDO -> {
            StudyRoomUserConfigView view = new StudyRoomUserConfigView();
            view.setTenantId(settingDO.getTenantId());
            view.setUserId(settingDO.getUserId());
            view.setConfig(this.parseConfig(settingDO.getConfigJson()));
            return view;
        }).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(Long userId, StudyRoomUserSettingReq req) {
        StudyRoomUserSettingDO exist = this.getEntity(userId);
        StudyRoomUserConfigBO merged = Objects.isNull(exist)
            ? this.defaultConfig()
            : this.parseConfig(exist.getConfigJson());
        boolean tokenChanged = this.mergeConfig(merged, req);
        String configJson = JsonUtils.toJsonStr(merged);
        if (Objects.nonNull(exist)) {
            exist.setConfigJson(configJson);
            settingMapper.updateById(exist);
        } else {
            StudyRoomUserSettingDO entity = new StudyRoomUserSettingDO();
            entity.setUserId(userId);
            entity.setConfigJson(configJson);
            settingMapper.insert(entity);
        }
        if (tokenChanged && StrUtil.isNotEmpty(merged.getToken())) {
            studyRoomUserClient.userDetail();
        }
    }

    private StudyRoomUserSettingDO getEntity(Long userId) {
        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserSettingDO::getUserId, userId);
        return settingMapper.selectOne(lqw);
    }

    /**
     * 将请求中的非空配置项合并到已有配置
     *
     * @param target 已有配置，合并结果写回该对象
     * @param source 请求配置
     * @return token 是否发生变化
     */
    private boolean mergeConfig(StudyRoomUserConfigBO target, StudyRoomUserSettingReq source) {
        if (Objects.nonNull(source.getReleaseTime())) {
            target.setReleaseTime(source.getReleaseTime());
        }
        if (Objects.nonNull(source.getSignInEnable())) {
            target.setSignInEnable(source.getSignInEnable());
        }
        if (Objects.isNull(source.getToken())) {
            return false;
        }
        String token = StrUtil.trimToNull(source.getToken());
        boolean tokenChanged = !Objects.equals(target.getToken(), token);
        target.setToken(token);
        return tokenChanged;
    }

    private StudyRoomUserConfigBO parseConfig(String configJson) {
        StudyRoomUserConfigBO config = JsonUtils.toBean(configJson, StudyRoomUserConfigBO.class);
        if (Objects.isNull(config)) {
            return this.defaultConfig();
        }
        StudyRoomUserConfigBO defaultConfig = this.defaultConfig();
        if (Objects.isNull(config.getReleaseTime())) {
            config.setReleaseTime(defaultConfig.getReleaseTime());
        }
        if (Objects.isNull(config.getSignInEnable())) {
            config.setSignInEnable(defaultConfig.getSignInEnable());
        }
        return config;
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

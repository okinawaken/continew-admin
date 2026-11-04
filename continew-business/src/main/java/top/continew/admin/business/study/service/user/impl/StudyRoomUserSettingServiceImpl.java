package top.continew.admin.business.study.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
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
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigViewBO;
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
        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserSettingDO::getUserId, userId);
        StudyRoomUserSettingDO studyRoomUserSettingDO = settingMapper.selectOne(lqw);
        if (Objects.isNull(studyRoomUserSettingDO)) {
            return this.defaultConfig();
        }
        return JsonUtils.toBean(studyRoomUserSettingDO.getConfigJson(),
            StudyRoomUserConfigBO.class);
    }

    @Override
    @TenantIgnore
    public List<StudyRoomUserConfigViewBO> listAllConfigs() {
        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        List<StudyRoomUserSettingDO> settingList = settingMapper.selectList(lqw);
        return settingList.stream().map(studyRoomUserSettingDO -> {
            StudyRoomUserConfigViewBO view = new StudyRoomUserConfigViewBO();
            view.setTenantId(studyRoomUserSettingDO.getTenantId());
            view.setUserId(studyRoomUserSettingDO.getUserId());
            view.setConfig(JsonUtils.toBean(studyRoomUserSettingDO.getConfigJson(),
                StudyRoomUserConfigBO.class));
            return view;
        }).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(Long userId, StudyRoomUserSettingReq req) {
        StudyRoomUserConfigBO config = BeanUtil.copyProperties(req, StudyRoomUserConfigBO.class);

        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserSettingDO::getUserId, userId);
        StudyRoomUserSettingDO existDO = settingMapper.selectOne(lqw);
        if (Objects.isNull(existDO)) {
            StudyRoomUserSettingDO studyRoomUserSettingDO = new StudyRoomUserSettingDO();
            studyRoomUserSettingDO.setUserId(userId);
            studyRoomUserSettingDO.setConfigJson(JsonUtils.toJsonStr(config));
            settingMapper.insert(studyRoomUserSettingDO);
        } else {
            existDO.setConfigJson(JsonUtils.toJsonStr(config));
            settingMapper.updateById(existDO);
        }
    }

    private StudyRoomUserConfigBO defaultConfig() {
        StudyRoomUserConfigBO studyRoomAutoReserveUserConfigBO = new StudyRoomUserConfigBO();
        studyRoomAutoReserveUserConfigBO.setReleaseTime(LocalTime.of(20, 0, 0));
        studyRoomAutoReserveUserConfigBO
            .setSignInEnable(StudyRoomAutoSignInEnableEnum.DISABLED.getValue());
        studyRoomAutoReserveUserConfigBO.setToken(StrUtil.EMPTY);
        return studyRoomAutoReserveUserConfigBO;
    }
}

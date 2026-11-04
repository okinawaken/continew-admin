package top.continew.admin.business.study.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.business.study.client.http.v3.StudyRoomUserV3Client;
import top.continew.admin.business.study.enums.StudyRoomAutoSignInEnableEnum;
import top.continew.admin.business.study.mapper.StudyRoomUserSettingMapper;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;
import top.continew.admin.business.study.model.entity.StudyRoomUserSettingDO;
import top.continew.admin.business.study.model.req.StudyRoomUserSettingReq;
import top.continew.admin.business.study.model.resp.StudyRoomUserConfigResp;
import top.continew.admin.business.study.service.user.StudyRoomUserSettingService;
import top.continew.starter.extension.tenant.annotation.TenantIgnore;
import top.continew.starter.extension.tenant.context.TenantContextHolder;
import top.continew.starter.json.jackson.util.JsonUtils;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * 自习室用户设置服务实现
 *
 * @author bixiu
 * @since 4.2.0
 */
@Service
@RequiredArgsConstructor
public class StudyRoomUserSettingServiceImpl implements StudyRoomUserSettingService {

    private final StudyRoomUserSettingMapper settingMapper;
    private final StudyRoomUserV3Client userV3Client;

    @Override
    public StudyRoomUserConfigResp getConfig(Long userId) {
        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserSettingDO::getUserId, userId);
        StudyRoomUserSettingDO studyRoomUserSettingDO = settingMapper.selectOne(lqw);
        if (Objects.isNull(studyRoomUserSettingDO)) {
            StudyRoomUserConfigResp resp = new StudyRoomUserConfigResp();
            resp.setTenantId(TenantContextHolder.getTenantId());
            resp.setUserId(userId);
            resp.setConfig(this.defaultConfig());
            return resp;
        }
        StudyRoomUserConfigResp resp = new StudyRoomUserConfigResp();
        resp.setTenantId(studyRoomUserSettingDO.getTenantId());
        resp.setUserId(studyRoomUserSettingDO.getUserId());
        resp.setConfig(
            JsonUtils.toBean(studyRoomUserSettingDO.getConfigJson(), StudyRoomUserConfigBO.class));
        return resp;
    }

    @Override
    @TenantIgnore
    public List<StudyRoomUserConfigResp> listAllConfigs() {
        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        List<StudyRoomUserSettingDO> settingList = settingMapper.selectList(lqw);
        return settingList.stream().map(studyRoomUserSettingDO -> {
            StudyRoomUserConfigResp configResp = new StudyRoomUserConfigResp();
            configResp.setTenantId(studyRoomUserSettingDO.getTenantId());
            configResp.setUserId(studyRoomUserSettingDO.getUserId());
            configResp.setConfig(JsonUtils.toBean(studyRoomUserSettingDO.getConfigJson(),
                StudyRoomUserConfigBO.class));
            return configResp;
        }).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(Long userId, StudyRoomUserSettingReq req) {
        userV3Client.userDetail(req.getToken());
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

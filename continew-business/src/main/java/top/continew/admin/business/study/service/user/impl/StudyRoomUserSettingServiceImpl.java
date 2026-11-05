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
import top.continew.admin.business.study.model.query.StudyRoomUserSettingQuery;
import top.continew.admin.business.study.model.req.StudyRoomUserSettingReq;
import top.continew.admin.business.study.model.resp.StudyRoomUserConfigResp;
import top.continew.admin.business.study.service.user.StudyRoomUserSettingService;
import top.continew.admin.common.base.service.BaseServiceImpl;
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
// @formatter:off
public class StudyRoomUserSettingServiceImpl extends
    BaseServiceImpl<StudyRoomUserSettingMapper, StudyRoomUserSettingDO, StudyRoomUserConfigResp,
        StudyRoomUserConfigResp, StudyRoomUserSettingQuery, StudyRoomUserSettingReq>
    implements StudyRoomUserSettingService {
    // @formatter:on

    private final StudyRoomUserV3Client userV3Client;

    /**
     * 读取用户配置，无记录时返回默认值
     *
     * @param userId 系统用户 ID
     * @return 详情数据
     */
    @Override
    public StudyRoomUserConfigResp get(Long userId) {
        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserSettingDO::getUserId, userId);
        StudyRoomUserSettingDO studyRoomUserSettingDO = baseMapper.selectOne(lqw);
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
        List<StudyRoomUserSettingDO> settingList = baseMapper.selectList(lqw);
        return settingList.stream().map(studyRoomUserSettingDO -> {
            StudyRoomUserConfigResp configResp = new StudyRoomUserConfigResp();
            configResp.setTenantId(studyRoomUserSettingDO.getTenantId());
            configResp.setUserId(studyRoomUserSettingDO.getUserId());
            configResp.setConfig(JsonUtils.toBean(studyRoomUserSettingDO.getConfigJson(),
                StudyRoomUserConfigBO.class));
            return configResp;
        }).toList();
    }

    /**
     * 局部更新用户配置
     * <p>
     * 只写入非空项，未传的配置保留原值；token 传空字符串表示解绑，传非空值时立即调用上游用户详情校验。
     *
     * @param req 新配置
     * @param userId 系统用户 ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(StudyRoomUserSettingReq req, Long userId) {
        userV3Client.userDetail(req.getToken());
        StudyRoomUserConfigBO config = BeanUtil.copyProperties(req, StudyRoomUserConfigBO.class);

        LambdaQueryWrapper<StudyRoomUserSettingDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserSettingDO::getUserId, userId);
        StudyRoomUserSettingDO existDO = baseMapper.selectOne(lqw);
        if (Objects.isNull(existDO)) {
            StudyRoomUserSettingDO studyRoomUserSettingDO = new StudyRoomUserSettingDO();
            studyRoomUserSettingDO.setUserId(userId);
            studyRoomUserSettingDO.setConfigJson(JsonUtils.toJsonStr(config));
            baseMapper.insert(studyRoomUserSettingDO);
        } else {
            existDO.setConfigJson(JsonUtils.toJsonStr(config));
            baseMapper.updateById(existDO);
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

package top.continew.admin.business.study.controller.user;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;
import top.continew.admin.business.study.model.req.StudyRoomUserSettingReqDTO;
import top.continew.admin.business.study.model.resp.StudyRoomUserSettingVO;
import top.continew.admin.business.study.service.user.StudyRoomUserSettingService;
import top.continew.admin.common.context.UserContextHolder;
import top.continew.starter.web.model.R;

import jakarta.validation.Valid;

/**
 * 用户设置：小程序 token、放号时刻与自动签到开关
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/business/study/user-setting")
public class StudyRoomUserSettingController {

    private final StudyRoomUserSettingService settingService;

    /**
     * 读取当前用户设置，未设置时返回默认值
     *
     * @return 详情数据
     */
    @SaCheckPermission("business:study:userSetting:get")
    @GetMapping(value = {"", "/"})
    public R<StudyRoomUserSettingVO> getSetting() {
        StudyRoomUserConfigBO config =
            settingService.getConfig(UserContextHolder.getUserId());
        StudyRoomUserSettingVO vo =
            BeanUtil.copyProperties(config, StudyRoomUserSettingVO.class);
        return R.ok(vo);
    }

    /**
     * 局部更新当前用户设置
     *
     * @param dto 请求数据
     * @return 响应数据
     */
    @SaCheckPermission("business:study:userSetting:update")
    @PutMapping(value = {"", "/"})
    public R<Void> updateSetting(@Valid @RequestBody StudyRoomUserSettingReqDTO dto) {
        StudyRoomUserConfigBO config = BeanUtil
            .copyProperties(dto, StudyRoomUserConfigBO.class);
        settingService.updateConfig(UserContextHolder.getUserId(), config);
        return R.ok();
    }
}

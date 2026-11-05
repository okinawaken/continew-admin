package top.continew.admin.business.study.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.business.study.model.query.StudyRoomUserSettingQuery;
import top.continew.admin.business.study.model.req.StudyRoomUserSettingReq;
import top.continew.admin.business.study.model.resp.StudyRoomUserConfigResp;
import top.continew.admin.business.study.service.user.StudyRoomUserSettingService;
import top.continew.admin.common.base.controller.BaseController;
import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.starter.extension.crud.enums.Api;

/**
 * 用户设置 API：小程序 token、放号时刻与自动签到开关
 * <p>
 * 以系统用户 ID 作为资源 ID，未落库的用户配置读取时返回默认值。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Tag(name = "用户设置 API")
@RestController
@CrudRequestMapping(value = "/business/study/user-setting", api = {Api.GET, Api.UPDATE})
// @formatter:off
public class StudyRoomUserSettingController extends
    BaseController<StudyRoomUserSettingService, StudyRoomUserConfigResp,
        StudyRoomUserConfigResp, StudyRoomUserSettingQuery, StudyRoomUserSettingReq> {
    // @formatter:on

}

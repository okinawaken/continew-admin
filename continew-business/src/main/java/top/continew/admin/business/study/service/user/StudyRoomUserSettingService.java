package top.continew.admin.business.study.service.user;

import top.continew.admin.business.study.model.query.StudyRoomUserSettingQuery;
import top.continew.admin.business.study.model.req.StudyRoomUserSettingReq;
import top.continew.admin.business.study.model.resp.StudyRoomUserConfigResp;
import top.continew.admin.common.base.service.BaseService;

import java.util.List;

/**
 * 自习室用户设置服务
 * <p>
 * 用户设置以系统用户 ID 作为资源 ID，详情与修改接口的 ID 参数即为用户 ID。
 *
 * @author bixiu
 * @since 4.2.0
 */
// @formatter:off
public interface StudyRoomUserSettingService extends
    BaseService<StudyRoomUserConfigResp, StudyRoomUserConfigResp, StudyRoomUserSettingQuery,
        StudyRoomUserSettingReq> {
    // @formatter:on

    /**
     * 读取全部用户配置（跨租户扫描）
     * <p>
     * 仅包含已落库的配置；未落库的用户不在结果中，其默认值请用 {@link #get(Long)} 获取。
     *
     * @return 列表数据
     */
    List<StudyRoomUserConfigResp> listAllConfigs();
}

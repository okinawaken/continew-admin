package top.continew.admin.business.study.service.user;

import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigViewBO;
import top.continew.admin.business.study.model.req.StudyRoomUserSettingReq;

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
    List<StudyRoomUserConfigViewBO> listAllConfigs();

    /**
     * 局部更新用户配置
     * <p>
     * 只写入非空项，未传的配置保留原值；token 传空字符串表示解绑，传非空值时立即调用上游用户详情校验。
     *
     * @param userId 系统用户 ID
     * @param req 新配置
     */
    void updateConfig(Long userId, StudyRoomUserSettingReq req);
}

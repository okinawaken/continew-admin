package top.continew.admin.business.study.model.bo;

import lombok.Data;

/**
 * 用户配置扫描视图
 * <p>
 * 定时任务需要跨租户扫描全部配置，因此除用户 ID 与配置内容外，额外携带租户 ID，
 * 供异步执行时恢复租户上下文。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
public class StudyRoomUserConfigViewBO {

    /**
     * 租户 ID
     */
    private Long tenantId;

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 自动预约用户配置
     */
    private StudyRoomUserConfigBO config;
}

package top.continew.admin.business.study.model.resp;

import lombok.Data;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;

/**
 * 用户配置响应
 * <p>
 * 除用户 ID 与配置内容外，额外携带租户 ID，供定时任务跨租户扫描后在异步执行时恢复租户上下文。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
public class StudyRoomUserConfigResp {

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

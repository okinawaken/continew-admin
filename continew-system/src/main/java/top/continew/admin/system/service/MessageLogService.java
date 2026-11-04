package top.continew.admin.system.service;

import java.util.List;

/**
 * 消息日志业务接口
 *
 * @author bixiu
 * @author bixiu
 * @since 1.3.0
 */
public interface MessageLogService {

    /**
     * 新增
     *
     * @param messageIds 消息 ID 列表
     * @param userId     用户 ID
     */
    void addWithUserId(List<Long> messageIds, Long userId);

    /**
     * 根据消息 ID 删除
     *
     * @param messageIds 消息 ID 列表
     */
    void deleteByMessageIds(List<Long> messageIds);
}

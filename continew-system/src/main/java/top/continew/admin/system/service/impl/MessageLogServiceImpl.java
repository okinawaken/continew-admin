package top.continew.admin.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.common.constant.GlobalConstants;
import top.continew.admin.system.mapper.MessageLogMapper;
import top.continew.admin.system.model.entity.MessageLogDO;
import top.continew.admin.system.service.MessageLogService;
import top.continew.starter.core.util.CollUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 消息日志业务实现
 *
 * @author bixiu
 * @author bixiu
 * @since 1.3.0
 */
@Service
@RequiredArgsConstructor
public class MessageLogServiceImpl implements MessageLogService {

    private final MessageLogMapper baseMapper;

    @Override
    public void addWithUserId(List<Long> messageIds, Long userId) {
        if (CollUtil.isEmpty(messageIds)) {
            return;
        }
        List<MessageLogDO> list = CollUtils
            .mapToList(messageIds,
                messageId -> new MessageLogDO(messageId, userId,
                    LocalDateTime.now(GlobalConstants.DEFAULT_ZONE_ID)));
        baseMapper.insert(list);
    }

    @Override
    public void deleteByMessageIds(List<Long> messageIds) {
        if (CollUtil.isEmpty(messageIds)) {
            return;
        }
        baseMapper.lambdaUpdate().in(MessageLogDO::getMessageId, messageIds).remove();
    }
}

package top.continew.admin.business.study.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.model.event.StudyRoomAutoReserveExecuteEvent;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveExecutorService;
import top.continew.starter.extension.tenant.util.TenantUtils;

import java.util.Objects;

/**
 * 自动预约执行事件监听器
 * <p>
 * 异步监听任务服务与调度器发布的执行事件，恢复事件携带的租户上下文后转交执行器处理。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveExecuteListener {

    private final StudyRoomAutoReserveExecutorService executorService;

    /**
     * 处理自动预约执行事件
     *
     * @param event 执行事件
     */
    @Async
    @EventListener
    public void onExecuteEvent(StudyRoomAutoReserveExecuteEvent event) {
        log.info("收到自动预约执行事件：taskId={}, triggerType={}", event.getTaskId(), event.getTriggerType());
        Long tenantId = Objects.requireNonNullElse(event.getTenantId(), 0L);
        TenantUtils.execute(tenantId,
            () -> executorService.execute(event.getTaskId(), event.getTriggerType()));
    }
}

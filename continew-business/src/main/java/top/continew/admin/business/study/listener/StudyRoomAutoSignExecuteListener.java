package top.continew.admin.business.study.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.model.event.StudyRoomAutoSignExecuteEvent;
import top.continew.admin.business.study.service.autosign.StudyRoomAutoSignExecutorService;
import top.continew.starter.extension.tenant.util.TenantUtils;
import top.continew.starter.json.jackson.util.JsonUtils;

/**
 * 自动签到执行事件监听器
 * <p>
 * 异步监听调度器发布的执行事件，恢复事件携带的租户上下文后转交执行器处理。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignExecuteListener {

    private final StudyRoomAutoSignExecutorService executorService;

    /**
     * 处理自动签到执行事件
     *
     * @param event 执行事件
     */
    @Async
    @EventListener
    public void onExecuteEvent(StudyRoomAutoSignExecuteEvent event) {
        log.info("收到自动签到执行事件：event={}", JsonUtils.toJsonStr(event));
        TenantUtils.execute(event.getTenantId(), () -> executorService.execute(event.getUserId()));
    }
}

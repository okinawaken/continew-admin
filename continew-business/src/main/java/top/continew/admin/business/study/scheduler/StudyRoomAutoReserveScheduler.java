package top.continew.admin.business.study.scheduler;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveTriggerTypeEnum;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;
import top.continew.admin.business.study.model.event.StudyRoomAutoReserveExecuteEvent;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRecordInfoResp;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import top.continew.admin.business.study.service.user.StudyRoomUserSettingService;
import top.continew.starter.extension.tenant.util.TenantUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * 自动预约调度器
 * <p>
 * 每分钟整点（第 0 秒）扫描所有启用中的任务：用户放号时刻已过、且当天尚未执行过，才发布执行事件。
 * 执行器监听事件并异步运行，扫描本身不等待结果；重启后只要当天还没落执行记录，仍会补跑。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveScheduler {

    private final StudyRoomAutoReserveTaskService taskService;
    private final StudyRoomUserSettingService settingService;
    private final StudyRoomAutoReserveTaskRecordService recordService;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 扫描启用中的任务并发布执行事件
     */
    @Scheduled(cron = "0 * * * * ?")
    public void scan() {
        List<StudyRoomAutoReserveTaskInfoResp> taskList = taskService.listEnabledTasks();
        for (StudyRoomAutoReserveTaskInfoResp task : taskList) {
            LocalDate nowDate = LocalDate.now();
            LocalTime nowTime = LocalTime.now();
            StudyRoomUserConfigBO config = settingService.getConfig(task.getUserId());
            if (nowTime.isBefore(config.getReleaseTime())) {
                return;
            }
            List<StudyRoomAutoReserveTaskRecordInfoResp> recordList = recordService
                    .listByTaskIdAndCreateDate(task.getId(), nowDate);
            if (CollUtil.isNotEmpty(recordList)) {
                return;
            }
            eventPublisher.publishEvent(new StudyRoomAutoReserveExecuteEvent(task.getId(),
                    StudyRoomAutoReserveTriggerTypeEnum.SCHEDULED.getValue(), task.getTenantId()));
        }
    }
}

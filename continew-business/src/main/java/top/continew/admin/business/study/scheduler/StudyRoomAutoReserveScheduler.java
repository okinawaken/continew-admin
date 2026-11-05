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
import top.continew.admin.business.study.model.resp.StudyRoomUserConfigResp;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import top.continew.admin.business.study.service.user.StudyRoomUserSettingService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * 自动预约调度器
 * <p>
 * 每分钟整点（第 0 秒）扫描所有启用中的任务：用户放号时刻已过、且当天尚未执行过，才发布执行事件。
 * 执行器监听事件并异步运行，扫描本身不等待结果；重启后只要当天还没落执行记录，仍会补跑。
 *
 * @author bixiu
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
        List<StudyRoomAutoReserveTaskInfoResp> taskInfoRespList = taskService.listEnabledTasks();
        for (StudyRoomAutoReserveTaskInfoResp taskInfoResp : taskInfoRespList) {
            LocalDate nowDate = LocalDate.now();
            LocalTime nowTime = LocalTime.now();
            StudyRoomUserConfigResp userConfigResp = settingService.get(taskInfoResp.getUserId());
            StudyRoomUserConfigBO config = userConfigResp.getConfig();
            if (!nowTime.isBefore(config.getReleaseTime())) {
                List<StudyRoomAutoReserveTaskRecordInfoResp> recordList = recordService
                    .listByTaskIdAndCreateDate(taskInfoResp.getId(), nowDate);
                if (CollUtil.isEmpty(recordList)) {
                    StudyRoomAutoReserveExecuteEvent event =
                        new StudyRoomAutoReserveExecuteEvent(taskInfoResp.getId(),
                            StudyRoomAutoReserveTriggerTypeEnum.SCHEDULED.getValue(),
                            taskInfoResp.getTenantId());
                    eventPublisher.publishEvent(event);
                }
            }
        }
    }
}

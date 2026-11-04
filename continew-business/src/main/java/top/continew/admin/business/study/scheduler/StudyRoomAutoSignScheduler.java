package top.continew.admin.business.study.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.enums.StudyRoomAutoSignInEnableEnum;
import top.continew.admin.business.study.model.bo.StudyRoomUserConfigBO;
import top.continew.admin.business.study.model.event.StudyRoomAutoSignExecuteEvent;
import top.continew.admin.business.study.model.resp.StudyRoomUserConfigResp;
import top.continew.admin.business.study.service.user.StudyRoomUserSettingService;

import java.util.List;

/**
 * 自动签到调度器
 *
 * @author bixiu
 * @since 4.2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignScheduler {

    private final StudyRoomUserSettingService settingService;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 扫描已开启自动签到的用户并发布执行事件
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void scan() {
        List<StudyRoomUserConfigResp> configViewBoList = settingService.listAllConfigs();
        for (StudyRoomUserConfigResp configViewBO : configViewBoList) {
            StudyRoomUserConfigBO config = configViewBO.getConfig();
            if (StudyRoomAutoSignInEnableEnum.isEnabled(config.getSignInEnable())) {
                eventPublisher.publishEvent(
                    new StudyRoomAutoSignExecuteEvent(configViewBO.getUserId(),
                        configViewBO.getTenantId()));
            }
        }
    }
}

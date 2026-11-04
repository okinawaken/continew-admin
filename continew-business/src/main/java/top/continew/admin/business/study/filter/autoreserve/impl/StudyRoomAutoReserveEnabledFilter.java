package top.continew.admin.business.study.filter.autoreserve.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveTaskEnableStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;

/**
 * 任务启用状态过滤器
 * <p>
 * 任务已暂停时直接终止链路，不写执行记录。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Slf4j
@Component
public class StudyRoomAutoReserveEnabledFilter implements StudyRoomAutoReserveFilter {

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.ENABLED.getValue();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskInfoResp taskRespDTO = context.getTaskRespDTO();
        if (StudyRoomAutoReserveTaskEnableStatusEnum.isPaused(taskRespDTO.getEnableStatus())) {
            return FilterResultDTO.fail("任务已暂停");
        }
        return FilterResultDTO.success(taskRespDTO);
    }
}

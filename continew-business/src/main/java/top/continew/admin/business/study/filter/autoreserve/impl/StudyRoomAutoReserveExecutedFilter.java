package top.continew.admin.business.study.filter.autoreserve.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRecordInfoResp;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;

import java.time.LocalDate;
import java.util.List;

/**
 * 今日已执行过滤器
 * <p>
 * 当天已有执行记录时终止链路并返回跳过结果；执行器仍会写入一条新的跳过记录。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveExecutedFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomAutoReserveTaskRecordService recordService;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.EXECUTED.getValue();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskInfoResp taskInfoResp = context.getTaskInfoResp();
        List<StudyRoomAutoReserveTaskRecordInfoResp> taskRecordInfoRespList =
            recordService.listByTaskIdAndCreateDate(taskInfoResp.getId(), LocalDate.now());
        if (CollUtil.isNotEmpty(taskRecordInfoRespList)) {
            return FilterResultDTO.fail("今日已执行");
        }
        return FilterResultDTO.success(taskRecordInfoRespList);
    }
}

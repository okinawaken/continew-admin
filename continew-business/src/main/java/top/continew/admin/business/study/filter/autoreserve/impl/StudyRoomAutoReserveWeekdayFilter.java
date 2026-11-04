package top.continew.admin.business.study.filter.autoreserve.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;

import java.time.LocalDate;

/**
 * 执行星期过滤器
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Slf4j
@Component
public class StudyRoomAutoReserveWeekdayFilter implements StudyRoomAutoReserveFilter {

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.WEEKDAY.getValue();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        LocalDate targetDate = context.getTargetDate();
        StudyRoomAutoReserveTaskInfoResp taskInfoResp = context.getTaskInfoResp();
        if (!CollUtil.contains(taskInfoResp.getWeekDays(), targetDate.getDayOfWeek().getValue())) {
            return FilterResultDTO.fail("目标日期不在执行计划内");
        }
        return FilterResultDTO.success(targetDate);
    }
}

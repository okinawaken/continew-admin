package top.continew.admin.business.study.filter.autoreserve.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.v2.StudyRoomReserveV2Client;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomReserveDateClientResp;
import top.continew.starter.core.util.validation.CheckUtils;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 目标日期与放号基线过滤器
 *
 * @author bixiu
 * @since 4.2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveTargetDateFilter implements StudyRoomAutoReserveFilter {

    private static final Pattern DATE_PREFIX_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}");

    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.TARGET_DATE.getValue();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskInfoResp taskInfoResp = context.getTaskInfoResp();
        StudyRoomClientResult<StudyRoomReserveDateClientResp> clientResult =
            reserveV2Client.reserveDateList(taskInfoResp.getVenueId(), taskInfoResp.getUserId());
        CheckUtils.throwIfNull(clientResult.getData(), "预约日期返回为空");

        LocalDate targetDate = this.resolveMaxReserveDate(clientResult.getData());
        context.setTargetDate(targetDate);

        if (!targetDate.isAfter(taskInfoResp.getLastMaxReserveDate())) {
            return FilterResultDTO.fail("未放出新日期的预约");
        }
        return FilterResultDTO.success(targetDate);
    }

    private LocalDate resolveMaxReserveDate(StudyRoomReserveDateClientResp reserveDateClientResp) {
        CheckUtils.throwIfEmpty(reserveDateClientResp.getReserveDateList(), "日期字符串返回为空");
        LocalDate maxDate = null;
        for (String reserveDate : reserveDateClientResp.getReserveDateList()) {
            Matcher matcher = DATE_PREFIX_PATTERN.matcher(reserveDate);
            CheckUtils.throwIf(!matcher.find(), "解析日期字符串失败");
            LocalDate date = LocalDate.parse(matcher.group());
            if (Objects.isNull(maxDate) || date.isAfter(maxDate)) {
                maxDate = date;
            }
        }
        return maxDate;
    }
}

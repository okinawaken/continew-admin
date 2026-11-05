package top.continew.admin.business.study.filter.autoreserve.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.v2.StudyRoomReserveV2Client;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.enums.StudyRoomSeatStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.req.StudyRoomSeatQueryClientReq;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomSeatAreaClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatMapClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatValidTimeClientResp;
import top.continew.starter.core.util.validation.CheckUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * 座位图查询与选座过滤器
 * <p>
 * 选座优先级：指定座位 → 偏好区域首个可约座位 → 全场首个可约座位 → 失败。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveSeatFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.SEAT.getValue();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskInfoResp taskInfoResp = context.getTaskInfoResp();
        LocalDate targetDate = context.getTargetDate();

        StudyRoomSeatQueryClientReq seatQueryClientReq = new StudyRoomSeatQueryClientReq();
        seatQueryClientReq.setVenueId(taskInfoResp.getVenueId());
        seatQueryClientReq.setReserveDateStr(targetDate);
        seatQueryClientReq.setReserveStartTimeStr(taskInfoResp.getReserveStartTime());
        seatQueryClientReq.setReserveEndTimeStr(taskInfoResp.getReserveEndTime());
        seatQueryClientReq.setReserveAnotherFlag(0);
        StudyRoomClientResult<StudyRoomSeatMapClientResp> clientResult =
            reserveV2Client.reserveV2SeatList(seatQueryClientReq, taskInfoResp.getUserId());
        CheckUtils.throwIfNull(clientResult.getData(), "座位图返回为空");

        StudyRoomSeatClientResp seatClientResp = this.selectSeat(context, clientResult.getData());
        context.setSeatClientResp(seatClientResp);
        if (Objects.isNull(seatClientResp)) {
            return FilterResultDTO.fail("期望时段无可预约座位");
        }
        return FilterResultDTO.success(seatClientResp);
    }

    private StudyRoomSeatClientResp selectSeat(StudyRoomAutoReserveContext context,
        StudyRoomSeatMapClientResp seatMapClientResp) {
        StudyRoomAutoReserveTaskInfoResp taskInfoResp = context.getTaskInfoResp();
        StudyRoomSeatClientResp areaSeatClientResp = null;
        StudyRoomSeatClientResp anySeatClientResp = null;

        for (StudyRoomSeatAreaClientResp seatAreaClientResp : seatMapClientResp
            .getVenueAreaList()) {
            boolean isPreferredArea =
                Objects.equals(seatAreaClientResp.getId(), taskInfoResp.getAreaId());
            for (StudyRoomSeatClientResp seatClientResp : seatAreaClientResp
                .getVenueAreaSeatList()) {
                boolean seatAvailable = this.isSeatAvailable(seatClientResp,
                    taskInfoResp.getReserveStartTime(), taskInfoResp.getReserveEndTime());
                if (!seatAvailable) {
                    continue;
                }
                if (Objects.equals(seatClientResp.getId(), taskInfoResp.getSeatId())) {
                    return seatClientResp;
                }
                if (Objects.isNull(areaSeatClientResp) && isPreferredArea) {
                    areaSeatClientResp = seatClientResp;
                }
                if (Objects.isNull(anySeatClientResp)) {
                    anySeatClientResp = seatClientResp;
                }
            }
        }
        return Objects.nonNull(areaSeatClientResp) ? areaSeatClientResp : anySeatClientResp;
    }

    private boolean isSeatAvailable(StudyRoomSeatClientResp seatClientResp, LocalTime startTime,
        LocalTime endTime) {
        if (!StudyRoomSeatStatusEnum.isCanReserve(seatClientResp.getSeatReserveState())) {
            return false;
        }

        for (StudyRoomSeatValidTimeClientResp validTime : seatClientResp.getValidTimeStrList()) {
            if (!startTime.isBefore(validTime.getValidStartTimeStr())
                && !endTime.isAfter(validTime.getValidEndTimeStr())) {
                return true;
            }
        }
        return false;
    }
}

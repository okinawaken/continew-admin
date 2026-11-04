package top.continew.admin.business.study.filter.autoreserve.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.StudyRoomReserveV2Client;
import top.continew.admin.business.study.client.http.model.req.StudyRoomSeatQueryReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomApiResult;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSeatAreaResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSeatMapResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSeatResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSeatValidTimeResp;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.enums.StudyRoomSeatStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRespDTO;
import top.continew.starter.core.exception.BusinessException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * 座位图查询与选座过滤器
 * <p>
 * 选座优先级：指定座位 → 偏好区域首个可约座位 → 全场首个可约座位 → 失败。
 *
 * @author Amadeus
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
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        LocalDate targetDate = context.getTargetDate();

        StudyRoomSeatQueryReq reqDTO = new StudyRoomSeatQueryReq();
        reqDTO.setVenueId(taskRespDTO.getVenueId());
        reqDTO.setReserveDateStr(targetDate);
        reqDTO.setReserveStartTimeStr(taskRespDTO.getReserveStartTime());
        reqDTO.setReserveEndTimeStr(taskRespDTO.getReserveEndTime());
        reqDTO.setReserveAnotherFlag(0);
        StudyRoomApiResult<StudyRoomSeatMapResp> resultDTO =
            reserveV2Client.reserveV2SeatList(reqDTO, taskRespDTO.getUserId());
        if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData())) {
            throw new BusinessException("座位图返回为空");
        }

        StudyRoomSeatResp seatRespDTO = this.selectSeat(context, resultDTO.getData());
        context.setSeatRespDTO(seatRespDTO);
        if (Objects.isNull(seatRespDTO)) {
            return FilterResultDTO.fail("期望时段无可预约座位");
        }
        return FilterResultDTO.success(seatRespDTO);
    }

    private StudyRoomSeatResp selectSeat(StudyRoomAutoReserveContext context,
        StudyRoomSeatMapResp seatMap) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        StudyRoomSeatResp areaSeatRespDTO = null;
        StudyRoomSeatResp anySeatRespDTO = null;

        for (StudyRoomSeatAreaResp seatAreaRespDTO : seatMap.getVenueAreaList()) {
            boolean isPreferredArea =
                Objects.equals(seatAreaRespDTO.getId(), taskRespDTO.getAreaId());
            for (StudyRoomSeatResp seatRespDTO : seatAreaRespDTO.getVenueAreaSeatList()) {
                boolean seatAvailable = this.isSeatAvailable(seatRespDTO,
                    taskRespDTO.getReserveStartTime(), taskRespDTO.getReserveEndTime());
                if (!seatAvailable) {
                    continue;
                }
                if (Objects.equals(seatRespDTO.getId(), taskRespDTO.getSeatId())) {
                    return seatRespDTO;
                }
                if (Objects.isNull(areaSeatRespDTO) && isPreferredArea) {
                    areaSeatRespDTO = seatRespDTO;
                }
                if (Objects.isNull(anySeatRespDTO)) {
                    anySeatRespDTO = seatRespDTO;
                }
            }
        }
        return Objects.nonNull(areaSeatRespDTO) ? areaSeatRespDTO : anySeatRespDTO;
    }

    private boolean isSeatAvailable(StudyRoomSeatResp seat, LocalTime startTime,
        LocalTime endTime) {
        if (!StudyRoomSeatStatusEnum.isCanReserve(seat.getSeatReserveState())) {
            return false;
        }

        for (StudyRoomSeatValidTimeResp validTime : seat.getValidTimeStrList()) {
            if (!startTime.isBefore(validTime.getValidStartTimeStr())
                && !endTime.isAfter(validTime.getValidEndTimeStr())) {
                return true;
            }
        }
        return false;
    }
}

package top.continew.admin.business.study.filter.autoreserve.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.continew.admin.business.study.client.http.StudyRoomReserveV2Client;
import top.continew.admin.business.study.model.req.StudyRoomSeatQueryClientReq;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomSeatAreaClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatMapClientResp;
import top.continew.admin.business.study.model.resp.StudyRoomSeatValidTimeClientResp;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import top.continew.admin.business.study.enums.StudyRoomSeatStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
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
        StudyRoomAutoReserveTaskInfoResp taskRespDTO = context.getTaskRespDTO();
        LocalDate targetDate = context.getTargetDate();

        StudyRoomSeatQueryClientReq reqDTO = new StudyRoomSeatQueryClientReq();
        reqDTO.setVenueId(taskRespDTO.getVenueId());
        reqDTO.setReserveDateStr(targetDate);
        reqDTO.setReserveStartTimeStr(taskRespDTO.getReserveStartTime());
        reqDTO.setReserveEndTimeStr(taskRespDTO.getReserveEndTime());
        reqDTO.setReserveAnotherFlag(0);
        StudyRoomClientResult<StudyRoomSeatMapClientResp> resultDTO =
            reserveV2Client.reserveV2SeatList(reqDTO, taskRespDTO.getUserId());
        if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData())) {
            throw new BusinessException("座位图返回为空");
        }

        StudyRoomSeatClientResp seatRespDTO = this.selectSeat(context, resultDTO.getData());
        context.setSeatRespDTO(seatRespDTO);
        if (Objects.isNull(seatRespDTO)) {
            return FilterResultDTO.fail("期望时段无可预约座位");
        }
        return FilterResultDTO.success(seatRespDTO);
    }

    private StudyRoomSeatClientResp selectSeat(StudyRoomAutoReserveContext context,
        StudyRoomSeatMapClientResp seatMap) {
        StudyRoomAutoReserveTaskInfoResp taskRespDTO = context.getTaskRespDTO();
        StudyRoomSeatClientResp areaSeatRespDTO = null;
        StudyRoomSeatClientResp anySeatRespDTO = null;

        for (StudyRoomSeatAreaClientResp seatAreaRespDTO : seatMap.getVenueAreaList()) {
            boolean isPreferredArea =
                Objects.equals(seatAreaRespDTO.getId(), taskRespDTO.getAreaId());
            for (StudyRoomSeatClientResp seatRespDTO : seatAreaRespDTO.getVenueAreaSeatList()) {
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

    private boolean isSeatAvailable(StudyRoomSeatClientResp seat, LocalTime startTime,
        LocalTime endTime) {
        if (!StudyRoomSeatStatusEnum.isCanReserve(seat.getSeatReserveState())) {
            return false;
        }

        for (StudyRoomSeatValidTimeClientResp validTime : seat.getValidTimeStrList()) {
            if (!startTime.isBefore(validTime.getValidStartTimeStr())
                && !endTime.isAfter(validTime.getValidEndTimeStr())) {
                return true;
            }
        }
        return false;
    }
}

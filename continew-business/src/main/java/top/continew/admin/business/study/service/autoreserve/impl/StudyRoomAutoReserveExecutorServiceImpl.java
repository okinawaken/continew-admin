package top.continew.admin.business.study.service.autoreserve.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.business.study.client.http.StudyRoomReserveV2Client;
import top.continew.admin.business.study.client.http.model.req.StudyRoomReserveConfirmClientReq;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomReserveConfirmClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomSeatClientResp;
import top.continew.admin.business.study.client.http.model.resp.StudyRoomVenueClientResp;
import top.continew.admin.business.study.constant.StudyRoomCacheConstants;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveTaskRecordStatusEnum;
import top.continew.admin.business.study.filter.FilterResultDTO;
import top.continew.admin.business.study.filter.autoreserve.StudyRoomAutoReserveFilterChain;
import top.continew.admin.business.study.model.context.StudyRoomAutoReserveContext;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRecordInfoResp;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveExecutorService;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import top.continew.starter.cache.redisson.util.RedisUtils;
import top.continew.starter.json.jackson.util.JsonUtils;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 自动预约执行器实现
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudyRoomAutoReserveExecutorServiceImpl
    implements StudyRoomAutoReserveExecutorService {

    private final StudyRoomReserveV2Client reserveV2Client;
    private final StudyRoomAutoReserveTaskService taskService;
    private final StudyRoomAutoReserveTaskRecordService recordService;
    private final StudyRoomAutoReserveFilterChain filterChain;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(Long taskId, Integer triggerType) {
        StudyRoomAutoReserveTaskInfoResp taskRespDTO = taskService.getTask(taskId);

        String lockKey = StudyRoomCacheConstants.AUTO_RESERVE_EXECUTE_LOCK + taskId;
        RLock lock = RedisUtils.getClient().getLock(lockKey);
        if (!lock.tryLock()) {
            log.error("自动预约任务正在执行中，加锁失败，忽略，taskId={}，triggerType={}", taskId, triggerType);
            return;
        }

        StudyRoomAutoReserveContext context =
            new StudyRoomAutoReserveContext(taskRespDTO, triggerType);

        try {
            FilterResultDTO<?> filterResultDTO = filterChain.doFilter(context);
            if (!filterResultDTO.isSuccess()) {
                StudyRoomAutoReserveTaskRecordInfoResp taskRecordRespDTO =
                    this.buildTaskRecordRespDTO(context,
                        StudyRoomAutoReserveTaskRecordStatusEnum.SKIPPED,
                        filterResultDTO.getErrorMsg());
                recordService.saveRecord(taskRecordRespDTO);
                return;
            }

            LocalDate targetDate = context.getTargetDate();
            StudyRoomVenueClientResp venueRespDTO = context.getVenueRespDTO();
            StudyRoomSeatClientResp seatRespDTO = context.getSeatRespDTO();

            StudyRoomReserveConfirmClientReq reqDTO = new StudyRoomReserveConfirmClientReq();
            reqDTO.setSeatId(seatRespDTO.getId());
            reqDTO.setReserveDateStr(targetDate);
            reqDTO.setReserveStartTimeStr(taskRespDTO.getReserveStartTime());
            reqDTO.setReserveEndTimeStr(taskRespDTO.getReserveEndTime());
            reqDTO.setCurrentLng(venueRespDTO.getVenueLng());
            reqDTO.setCurrentLat(venueRespDTO.getVenueLat());
            reqDTO.setReserveAnotherFlag(0);
            StudyRoomClientResult<StudyRoomReserveConfirmClientResp> resultDTO =
                reserveV2Client.reserveV2SeatConfirm(reqDTO, taskRespDTO.getUserId());
            context.setConfirmRespDTO(resultDTO.getData());

            StudyRoomAutoReserveTaskRecordInfoResp taskRecordRespDTO = this.buildTaskRecordRespDTO(
                context, StudyRoomAutoReserveTaskRecordStatusEnum.SUCCESS, null);
            recordService.saveRecord(taskRecordRespDTO);
            taskService.advanceLastMaxReserveDate(taskId, context.getTargetDate());
        } catch (Exception e) {
            log.error("自动预约任务执行失败，taskId={}，triggerType={}, context={}", taskId, triggerType,
                JsonUtils.toJsonStr(context), e);
            StudyRoomAutoReserveTaskRecordInfoResp taskRecordRespDTO = this.buildTaskRecordRespDTO(
                context, StudyRoomAutoReserveTaskRecordStatusEnum.FAILED, e.getMessage());
            recordService.saveRecord(taskRecordRespDTO);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    private StudyRoomAutoReserveTaskRecordInfoResp buildTaskRecordRespDTO(
        StudyRoomAutoReserveContext context, StudyRoomAutoReserveTaskRecordStatusEnum statusEnum,
        String errorMsg) {
        StudyRoomAutoReserveTaskRecordInfoResp taskRecordRespDTO =
            new StudyRoomAutoReserveTaskRecordInfoResp();
        taskRecordRespDTO.setTriggerType(context.getTriggerType());
        taskRecordRespDTO.setTargetDate(context.getTargetDate());
        taskRecordRespDTO.setStatus(statusEnum.getValue());
        taskRecordRespDTO.setFailMsg(errorMsg);

        StudyRoomAutoReserveTaskInfoResp taskRespDTO = context.getTaskRespDTO();
        if (Objects.nonNull(taskRespDTO)) {
            taskRecordRespDTO.setTaskId(taskRespDTO.getId());
            taskRecordRespDTO.setUserId(taskRespDTO.getUserId());
            taskRecordRespDTO.setTimeRange(
                taskRespDTO.getReserveStartTime() + "-" + taskRespDTO.getReserveEndTime());
        }
        StudyRoomSeatClientResp seatRespDTO = context.getSeatRespDTO();
        if (Objects.nonNull(seatRespDTO)) {
            taskRecordRespDTO.setSeatId(seatRespDTO.getId());
            taskRecordRespDTO.setSeatNumber(seatRespDTO.getSeatNumber());
        }
        StudyRoomReserveConfirmClientResp confirmRespDTO = context.getConfirmRespDTO();
        if (Objects.nonNull(confirmRespDTO)) {
            taskRecordRespDTO.setUserReserveId(confirmRespDTO.getId());
        }
        return taskRecordRespDTO;
    }
}

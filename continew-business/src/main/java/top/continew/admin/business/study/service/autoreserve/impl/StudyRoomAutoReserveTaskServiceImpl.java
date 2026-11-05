package top.continew.admin.business.study.service.autoreserve.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.business.study.client.http.v1.StudyRoomVenueClient;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveTaskEnableStatusEnum;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveTriggerTypeEnum;
import top.continew.admin.business.study.mapper.StudyRoomAutoReserveTaskMapper;
import top.continew.admin.business.study.model.bo.StudyRoomAutoReserveTaskConfigBO;
import top.continew.admin.business.study.model.entity.StudyRoomAutoReserveTaskDO;
import top.continew.admin.business.study.model.event.StudyRoomAutoReserveExecuteEvent;
import top.continew.admin.business.study.model.query.StudyRoomAutoReserveTaskQuery;
import top.continew.admin.business.study.model.req.StudyRoomAutoReserveTaskReq;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.business.study.model.resp.StudyRoomClientResult;
import top.continew.admin.business.study.model.resp.StudyRoomVenueClientResp;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import top.continew.admin.common.base.service.BaseServiceImpl;
import top.continew.admin.common.context.UserContextHolder;
import top.continew.starter.core.util.validation.CheckUtils;
import top.continew.starter.extension.tenant.annotation.TenantIgnore;
import top.continew.starter.extension.tenant.context.TenantContextHolder;
import top.continew.starter.json.jackson.util.JsonUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * 自动预约任务服务实现
 *
 * @author bixiu
 * @since 4.2.0
 */
@Service
@RequiredArgsConstructor
// @formatter:off
public class StudyRoomAutoReserveTaskServiceImpl extends
    BaseServiceImpl<StudyRoomAutoReserveTaskMapper, StudyRoomAutoReserveTaskDO,
        StudyRoomAutoReserveTaskInfoResp, StudyRoomAutoReserveTaskInfoResp,
        StudyRoomAutoReserveTaskQuery, StudyRoomAutoReserveTaskReq>
    implements StudyRoomAutoReserveTaskService {
    // @formatter:on

    private final StudyRoomAutoReserveTaskRecordService recordService;
    private final StudyRoomVenueClient venueClient;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    protected QueryWrapper<StudyRoomAutoReserveTaskDO> buildQueryWrapper(
        StudyRoomAutoReserveTaskQuery query) {
        QueryWrapper<StudyRoomAutoReserveTaskDO> queryWrapper = super.buildQueryWrapper(query);
        // 任务列表仅展示当前用户的任务
        queryWrapper.lambda()
            .eq(StudyRoomAutoReserveTaskDO::getUserId, UserContextHolder.getUserId());
        return queryWrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(StudyRoomAutoReserveTaskReq req) {
        StudyRoomClientResult<StudyRoomVenueClientResp> venueResult =
            venueClient.reserveVenueDetail(req.getVenueId());
        CheckUtils.throwIfNull(venueResult, "未查询到门店数据");
        CheckUtils.throwIfNull(venueResult.getData(), "未查询到门店数据");
        StudyRoomAutoReserveTaskConfigBO config = BeanUtil.copyProperties(req,
            StudyRoomAutoReserveTaskConfigBO.class);

        StudyRoomAutoReserveTaskDO taskDO = new StudyRoomAutoReserveTaskDO();
        taskDO.setUserId(UserContextHolder.getUserId());
        taskDO.setVenueId(req.getVenueId());
        taskDO.setVenueName(venueResult.getData().getVenueName());
        taskDO.setTaskJson(JsonUtils.toJsonStr(config));
        taskDO.setEnableStatus(StudyRoomAutoReserveTaskEnableStatusEnum.ENABLED.getValue());
        baseMapper.insert(taskDO);
        return taskDO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(StudyRoomAutoReserveTaskReq req, Long id) {
        StudyRoomAutoReserveTaskDO taskDO = this.getById(id);
        StudyRoomClientResult<StudyRoomVenueClientResp> venueResult =
            venueClient.reserveVenueDetail(req.getVenueId());
        CheckUtils.throwIfNull(venueResult, "未查询到门店数据");
        CheckUtils.throwIfNull(venueResult.getData(), "未查询到门店数据");
        StudyRoomAutoReserveTaskConfigBO config = BeanUtil.copyProperties(req,
            StudyRoomAutoReserveTaskConfigBO.class);

        taskDO.setVenueId(req.getVenueId());
        taskDO.setVenueName(venueResult.getData().getVenueName());
        taskDO.setTaskJson(JsonUtils.toJsonStr(config));
        baseMapper.updateById(taskDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        for (Long id : ids) {
            baseMapper.deleteById(id);
            recordService.deleteByTaskId(id);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pauseTask(Long taskId, String pauseReason) {
        StudyRoomAutoReserveTaskDO task = this.getById(taskId);

        task.setEnableStatus(StudyRoomAutoReserveTaskEnableStatusEnum.PAUSED.getValue());
        task.setPauseReason(pauseReason);
        baseMapper.updateById(task);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resumeTask(Long taskId) {
        StudyRoomAutoReserveTaskDO task = this.getById(taskId);

        task.setEnableStatus(StudyRoomAutoReserveTaskEnableStatusEnum.ENABLED.getValue());
        task.setPauseReason(StrUtil.EMPTY);
        baseMapper.updateById(task);
    }

    @Override
    public void executeTask(Long taskId) {
        StudyRoomAutoReserveTaskDO task = this.getById(taskId);
        CheckUtils.throwIf(!StudyRoomAutoReserveTaskEnableStatusEnum.isEnabled(task
            .getEnableStatus()), "任务已暂停，请先恢复");

        eventPublisher.publishEvent(new StudyRoomAutoReserveExecuteEvent(task.getId(),
            StudyRoomAutoReserveTriggerTypeEnum.MANUAL.getValue(),
            TenantContextHolder.getTenantId()));
    }

    @Override
    @TenantIgnore
    public List<StudyRoomAutoReserveTaskInfoResp> listEnabledTasks() {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskDO::getEnableStatus,
            StudyRoomAutoReserveTaskEnableStatusEnum.ENABLED.getValue());
        List<StudyRoomAutoReserveTaskDO> taskList = baseMapper.selectList(lqw);
        List<StudyRoomAutoReserveTaskInfoResp> taskRespList =
            BeanUtil.copyToList(taskList, StudyRoomAutoReserveTaskInfoResp.class);
        taskRespList.forEach(this::fill);
        return taskRespList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void advanceLastMaxReserveDate(Long taskId, LocalDate targetDate) {
        LambdaUpdateWrapper<StudyRoomAutoReserveTaskDO> luw = Wrappers.lambdaUpdate();
        luw.eq(StudyRoomAutoReserveTaskDO::getId, taskId)
            .lt(StudyRoomAutoReserveTaskDO::getLastMaxReserveDate, targetDate)
            .set(StudyRoomAutoReserveTaskDO::getLastMaxReserveDate, targetDate);
        baseMapper.update(luw);
    }

    @Override
    protected void fill(Object obj) {
        super.fill(obj);
        if (!(obj instanceof StudyRoomAutoReserveTaskInfoResp taskRespDTO)) {
            return;
        }
        StudyRoomAutoReserveTaskConfigBO config =
            JsonUtils.toBean(taskRespDTO.getTaskJson(), StudyRoomAutoReserveTaskConfigBO.class);
        if (Objects.nonNull(config)) {
            taskRespDTO.setSeatId(config.getSeatId());
            taskRespDTO.setSeatNumber(config.getSeatNumber());
            taskRespDTO.setAreaId(config.getAreaId());
            taskRespDTO.setAreaName(config.getAreaName());
            taskRespDTO.setReserveStartTime(config.getReserveStartTime());
            taskRespDTO.setReserveEndTime(config.getReserveEndTime());
            taskRespDTO.setWeekDays(config.getWeekDays());
        }
    }
}

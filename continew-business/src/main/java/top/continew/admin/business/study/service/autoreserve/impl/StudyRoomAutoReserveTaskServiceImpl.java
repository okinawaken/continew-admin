/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.business.study.service.autoreserve.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.business.study.client.http.StudyRoomVenueClient;
import top.continew.admin.business.study.client.model.StudyRoomApiResultDTO;
import top.continew.admin.business.study.client.model.StudyRoomVenueRespDTO;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveTaskEnableStatusEnum;
import top.continew.admin.business.study.enums.StudyRoomAutoReserveTriggerTypeEnum;
import top.continew.admin.business.study.mapper.StudyRoomAutoReserveTaskMapper;
import top.continew.admin.business.study.model.bo.StudyRoomAutoReserveTaskConfigBO;
import top.continew.admin.business.study.model.entity.StudyRoomAutoReserveTaskDO;
import top.continew.admin.business.study.model.event.StudyRoomAutoReserveExecuteEvent;
import top.continew.admin.business.study.model.req.StudyRoomAutoReserveTaskReqDTO;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRespDTO;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.tenant.annotation.TenantIgnore;
import top.continew.starter.extension.tenant.context.TenantContextHolder;
import top.continew.starter.json.jackson.util.JsonUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * 自动预约任务服务实现
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Service
@RequiredArgsConstructor
public class StudyRoomAutoReserveTaskServiceImpl implements StudyRoomAutoReserveTaskService {

    private final StudyRoomAutoReserveTaskMapper taskMapper;
    private final StudyRoomAutoReserveTaskRecordService recordService;
    private final StudyRoomVenueClient venueClient;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public IPage<StudyRoomAutoReserveTaskRespDTO> pageList(Long userId, PageQuery pageQuery) {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskDO::getUserId, userId)
            .orderByDesc(StudyRoomAutoReserveTaskDO::getCreateTime);
        Page<StudyRoomAutoReserveTaskDO> page =
            taskMapper.selectPage(new Page<>(pageQuery.getPage(), pageQuery.getSize()), lqw);
        return page.convert(this::toDto);
    }

    @Override
    public StudyRoomAutoReserveTaskRespDTO getTask(Long taskId) {
        StudyRoomAutoReserveTaskDO task = taskMapper.selectById(taskId);
        if (Objects.isNull(task)) {
            throw new BusinessException("未查询到任务数据");
        }
        return this.toDto(task);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTask(Long userId, StudyRoomAutoReserveTaskReqDTO dto) {
        StudyRoomApiResultDTO<StudyRoomVenueRespDTO> studyRoomResult =
            venueClient.reserveVenueDetail(dto.getVenueId());
        if (Objects.isNull(studyRoomResult) || Objects.isNull(studyRoomResult.getData())) {
            throw new BusinessException("未查询到门店数据");
        }

        StudyRoomVenueRespDTO venueRespDTO = studyRoomResult.getData();
        StudyRoomAutoReserveTaskConfigBO config =
            BeanUtil.copyProperties(dto, StudyRoomAutoReserveTaskConfigBO.class);

        StudyRoomAutoReserveTaskDO taskDO = new StudyRoomAutoReserveTaskDO();
        taskDO.setUserId(userId);
        taskDO.setVenueId(dto.getVenueId());
        taskDO.setVenueName(venueRespDTO.getVenueName());
        taskDO.setTaskJson(JsonUtils.toJsonStr(config));
        taskDO.setEnableStatus(StudyRoomAutoReserveTaskEnableStatusEnum.ENABLED.getValue());
        taskMapper.insert(taskDO);
        return taskDO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTask(Long taskId, StudyRoomAutoReserveTaskReqDTO dto) {
        StudyRoomApiResultDTO<StudyRoomVenueRespDTO> studyRoomResult =
            venueClient.reserveVenueDetail(dto.getVenueId());
        if (Objects.isNull(studyRoomResult) || Objects.isNull(studyRoomResult.getData())) {
            throw new BusinessException("未查询到门店数据");
        }
        StudyRoomAutoReserveTaskDO taskDO = taskMapper.selectById(taskId);
        if (Objects.isNull(taskDO)) {
            throw new BusinessException("未查询到任务数据");
        }
        StudyRoomVenueRespDTO venueRespDTO = studyRoomResult.getData();
        StudyRoomAutoReserveTaskConfigBO config =
            BeanUtil.copyProperties(dto, StudyRoomAutoReserveTaskConfigBO.class);

        taskDO.setVenueId(dto.getVenueId());
        taskDO.setVenueName(venueRespDTO.getVenueName());
        taskDO.setTaskJson(JsonUtils.toJsonStr(config));
        taskMapper.updateById(taskDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTask(Long taskId) {
        taskMapper.deleteById(taskId);
        recordService.deleteByTaskId(taskId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pauseTask(Long taskId, String pauseReason) {
        StudyRoomAutoReserveTaskDO task = taskMapper.selectById(taskId);
        if (Objects.isNull(task)) {
            throw new BusinessException("未查询到任务数据");
        }

        task.setEnableStatus(StudyRoomAutoReserveTaskEnableStatusEnum.PAUSED.getValue());
        task.setPauseReason(pauseReason);
        taskMapper.updateById(task);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resumeTask(Long taskId) {
        StudyRoomAutoReserveTaskDO task = taskMapper.selectById(taskId);
        if (Objects.isNull(task)) {
            throw new BusinessException("未查询到任务数据");
        }

        task.setEnableStatus(StudyRoomAutoReserveTaskEnableStatusEnum.ENABLED.getValue());
        task.setPauseReason(StrUtil.EMPTY);
        taskMapper.updateById(task);
    }

    @Override
    public void executeTask(Long taskId) {
        StudyRoomAutoReserveTaskDO task = taskMapper.selectById(taskId);
        if (Objects.isNull(task)) {
            throw new BusinessException("未查询到任务数据");
        }
        if (StudyRoomAutoReserveTaskEnableStatusEnum.isPaused(task.getEnableStatus())) {
            throw new BusinessException("任务已暂停，请先恢复");
        }
        eventPublisher.publishEvent(new StudyRoomAutoReserveExecuteEvent(task.getId(),
            StudyRoomAutoReserveTriggerTypeEnum.MANUAL.getValue(),
            TenantContextHolder.getTenantId()));
    }

    @Override
    @TenantIgnore
    public List<StudyRoomAutoReserveTaskRespDTO> listEnabledTasks() {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskDO::getEnableStatus,
            StudyRoomAutoReserveTaskEnableStatusEnum.ENABLED.getValue());
        List<StudyRoomAutoReserveTaskDO> taskList = taskMapper.selectList(lqw);
        return taskList.stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void advanceLastMaxReserveDate(Long taskId, LocalDate targetDate) {
        LambdaUpdateWrapper<StudyRoomAutoReserveTaskDO> luw = Wrappers.lambdaUpdate();
        luw.eq(StudyRoomAutoReserveTaskDO::getId, taskId)
            .lt(StudyRoomAutoReserveTaskDO::getLastMaxReserveDate, targetDate)
            .set(StudyRoomAutoReserveTaskDO::getLastMaxReserveDate, targetDate);
        taskMapper.update(luw);
    }

    private StudyRoomAutoReserveTaskRespDTO toDto(StudyRoomAutoReserveTaskDO task) {
        if (Objects.isNull(task)) {
            return null;
        }
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = new StudyRoomAutoReserveTaskRespDTO();
        taskRespDTO.setId(task.getId());
        taskRespDTO.setUserId(task.getUserId());
        taskRespDTO.setTenantId(task.getTenantId());
        taskRespDTO.setVenueId(task.getVenueId());
        taskRespDTO.setVenueName(task.getVenueName());
        taskRespDTO.setEnableStatus(task.getEnableStatus());
        taskRespDTO.setPauseReason(task.getPauseReason());
        taskRespDTO.setLastMaxReserveDate(task.getLastMaxReserveDate());
        taskRespDTO.setCreateTime(task.getCreateTime());

        StudyRoomAutoReserveTaskConfigBO config =
            JsonUtils.toBean(task.getTaskJson(), StudyRoomAutoReserveTaskConfigBO.class);
        if (Objects.nonNull(config)) {
            taskRespDTO.setSeatId(config.getSeatId());
            taskRespDTO.setSeatNumber(config.getSeatNumber());
            taskRespDTO.setAreaId(config.getAreaId());
            taskRespDTO.setAreaName(config.getAreaName());
            taskRespDTO.setReserveStartTime(config.getReserveStartTime());
            taskRespDTO.setReserveEndTime(config.getReserveEndTime());
            taskRespDTO.setWeekDays(config.getWeekDays());
        }
        return taskRespDTO;
    }
}

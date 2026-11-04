package top.continew.admin.business.study.service.autoreserve.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.business.study.mapper.StudyRoomAutoReserveTaskRecordMapper;
import top.continew.admin.business.study.model.bo.StudyRoomAutoReserveTaskRecordDetailBO;
import top.continew.admin.business.study.model.entity.StudyRoomAutoReserveTaskRecordDO;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRecordRespDTO;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import top.continew.starter.core.exception.BusinessException;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.json.jackson.util.JsonUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * 自动预约执行记录服务实现
 * <p>
 * 任务归属独立成 {@code task_id} 列，按任务查询与删除可以直接走数据库；执行细节仍在 {@code detail_json} 中。
 *
 * @author Amadeus
 * @since 4.2.0
 */
@Service
@RequiredArgsConstructor
public class StudyRoomAutoReserveTaskRecordServiceImpl
    implements StudyRoomAutoReserveTaskRecordService {

    private final StudyRoomAutoReserveTaskRecordMapper recordMapper;

    @Override
    public IPage<StudyRoomAutoReserveTaskRecordRespDTO> pageByTaskId(Long taskId,
        PageQuery pageQuery) {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskRecordDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskRecordDO::getTaskId, taskId)
            .orderByDesc(StudyRoomAutoReserveTaskRecordDO::getCreateTime);
        Page<StudyRoomAutoReserveTaskRecordDO> page =
            recordMapper.selectPage(new Page<>(pageQuery.getPage(), pageQuery.getSize()), lqw);
        return page.convert(this::toDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByTaskId(Long taskId) {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskRecordDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskRecordDO::getTaskId, taskId);
        recordMapper.delete(lqw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRecord(StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO) {
        StudyRoomAutoReserveTaskRecordDetailBO detailBO = BeanUtil
            .copyProperties(taskRecordRespDTO, StudyRoomAutoReserveTaskRecordDetailBO.class);

        StudyRoomAutoReserveTaskRecordDO recordDO = new StudyRoomAutoReserveTaskRecordDO();
        recordDO.setTaskId(taskRecordRespDTO.getTaskId());
        recordDO.setUserId(taskRecordRespDTO.getUserId());
        // 异步任务线程没有登录上下文，显式以任务归属用户作为创建人，避免 create_user 为空
        recordDO.setCreateUser(taskRecordRespDTO.getUserId());
        recordDO.setTriggerType(taskRecordRespDTO.getTriggerType());
        recordDO.setTargetDate(taskRecordRespDTO.getTargetDate());
        recordDO.setStatus(taskRecordRespDTO.getStatus());
        recordDO.setDetailJson(JsonUtils.toJsonStr(detailBO));
        recordMapper.insert(recordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRecord(StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO) {
        StudyRoomAutoReserveTaskRecordDO recordDO =
            recordMapper.selectById(taskRecordRespDTO.getId());
        if (Objects.isNull(recordDO)) {
            throw new BusinessException("未查询到任务记录数据");
        }

        StudyRoomAutoReserveTaskRecordDetailBO detailBO = BeanUtil
            .copyProperties(taskRecordRespDTO, StudyRoomAutoReserveTaskRecordDetailBO.class);
        recordDO.setStatus(taskRecordRespDTO.getStatus());
        recordDO.setDetailJson(JsonUtils.toJsonStr(detailBO));
        recordMapper.updateById(recordDO);
    }

    @Override
    public List<StudyRoomAutoReserveTaskRecordRespDTO> listByTaskIdAndCreateDate(Long taskId,
        LocalDate createDate) {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskRecordDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskRecordDO::getTaskId, taskId)
            .ge(StudyRoomAutoReserveTaskRecordDO::getCreateTime, createDate.atStartOfDay())
            .lt(StudyRoomAutoReserveTaskRecordDO::getCreateTime,
                createDate.plusDays(1).atStartOfDay())
            .orderByDesc(StudyRoomAutoReserveTaskRecordDO::getCreateTime);
        List<StudyRoomAutoReserveTaskRecordDO> recordList = recordMapper.selectList(lqw);
        return recordList.stream().map(this::toDto).toList();
    }

    private StudyRoomAutoReserveTaskRecordRespDTO toDto(StudyRoomAutoReserveTaskRecordDO record) {
        if (Objects.isNull(record)) {
            return null;
        }
        StudyRoomAutoReserveTaskRecordDetailBO detail =
            JsonUtils.toBean(record.getDetailJson(), StudyRoomAutoReserveTaskRecordDetailBO.class);

        StudyRoomAutoReserveTaskRecordRespDTO recordRespDTO =
            new StudyRoomAutoReserveTaskRecordRespDTO();
        recordRespDTO.setId(record.getId());
        recordRespDTO.setTaskId(record.getTaskId());
        recordRespDTO.setUserId(record.getUserId());
        recordRespDTO.setTriggerType(record.getTriggerType());
        recordRespDTO.setTargetDate(record.getTargetDate());
        recordRespDTO.setStatus(record.getStatus());
        recordRespDTO.setCreateTime(record.getCreateTime());
        if (Objects.nonNull(detail)) {
            recordRespDTO.setSeatId(detail.getSeatId());
            recordRespDTO.setSeatNumber(detail.getSeatNumber());
            recordRespDTO.setTimeRange(detail.getTimeRange());
            recordRespDTO.setUserReserveId(detail.getUserReserveId());
            recordRespDTO.setFailMsg(detail.getFailMsg());
        }
        return recordRespDTO;
    }
}

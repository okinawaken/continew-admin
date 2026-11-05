package top.continew.admin.business.study.service.autoreserve.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.business.study.mapper.StudyRoomAutoReserveTaskRecordMapper;
import top.continew.admin.business.study.model.bo.StudyRoomAutoReserveTaskRecordDetailBO;
import top.continew.admin.business.study.model.entity.StudyRoomAutoReserveTaskRecordDO;
import top.continew.admin.business.study.model.query.StudyRoomAutoReserveTaskRecordQuery;
import top.continew.admin.business.study.model.req.StudyRoomAutoReserveTaskRecordReq;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRecordInfoResp;
import top.continew.admin.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import top.continew.admin.common.base.service.BaseServiceImpl;
import top.continew.starter.core.util.validation.CheckUtils;
import top.continew.starter.json.jackson.util.JsonUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * 自动预约执行记录服务实现
 * <p>
 * 任务归属独立成 {@code task_id} 列，按任务查询与删除可以直接走数据库；执行细节仍在 {@code detail_json} 中。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Service
// @formatter:off
public class StudyRoomAutoReserveTaskRecordServiceImpl extends
    BaseServiceImpl<StudyRoomAutoReserveTaskRecordMapper, StudyRoomAutoReserveTaskRecordDO,
        StudyRoomAutoReserveTaskRecordInfoResp, StudyRoomAutoReserveTaskRecordInfoResp,
        StudyRoomAutoReserveTaskRecordQuery, StudyRoomAutoReserveTaskRecordReq>
    implements StudyRoomAutoReserveTaskRecordService {
    // @formatter:on

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByTaskId(Long taskId) {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskRecordDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskRecordDO::getTaskId, taskId);
        baseMapper.delete(lqw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRecord(StudyRoomAutoReserveTaskRecordInfoResp taskRecordRespDTO) {
        StudyRoomAutoReserveTaskRecordDetailBO detailBO = BeanUtil
            .copyProperties(taskRecordRespDTO, StudyRoomAutoReserveTaskRecordDetailBO.class);

        StudyRoomAutoReserveTaskRecordDO recordDO = new StudyRoomAutoReserveTaskRecordDO();
        recordDO.setTaskId(taskRecordRespDTO.getTaskId());
        recordDO.setUserId(taskRecordRespDTO.getUserId());
        recordDO.setTriggerType(taskRecordRespDTO.getTriggerType());
        recordDO.setTargetDate(taskRecordRespDTO.getTargetDate());
        recordDO.setStatus(taskRecordRespDTO.getStatus());
        recordDO.setDetailJson(JsonUtils.toJsonStr(detailBO));
        recordDO.setTenantId(taskRecordRespDTO.getTenantId());
        recordDO.setCreateUser(taskRecordRespDTO.getUserId());
        recordDO.setUpdateUser(taskRecordRespDTO.getUserId());
        baseMapper.insert(recordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRecord(StudyRoomAutoReserveTaskRecordInfoResp taskRecordRespDTO) {
        StudyRoomAutoReserveTaskRecordDO recordDO = baseMapper
            .selectById(taskRecordRespDTO.getId());
        CheckUtils.throwIfNull(recordDO, "未查询到任务记录数据");

        StudyRoomAutoReserveTaskRecordDetailBO detailBO = BeanUtil
            .copyProperties(taskRecordRespDTO, StudyRoomAutoReserveTaskRecordDetailBO.class);
        recordDO.setStatus(taskRecordRespDTO.getStatus());
        recordDO.setDetailJson(JsonUtils.toJsonStr(detailBO));
        baseMapper.updateById(recordDO);
    }

    @Override
    public List<StudyRoomAutoReserveTaskRecordInfoResp> listByTaskIdAndCreateDate(Long taskId,
        LocalDate createDate) {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskRecordDO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskRecordDO::getTaskId, taskId)
            .ge(StudyRoomAutoReserveTaskRecordDO::getCreateTime, createDate.atStartOfDay())
            .lt(StudyRoomAutoReserveTaskRecordDO::getCreateTime,
                createDate.plusDays(1).atStartOfDay())
            .orderByDesc(StudyRoomAutoReserveTaskRecordDO::getCreateTime);
        List<StudyRoomAutoReserveTaskRecordDO> recordList = baseMapper.selectList(lqw);
        List<StudyRoomAutoReserveTaskRecordInfoResp> recordRespList =
            BeanUtil.copyToList(recordList, StudyRoomAutoReserveTaskRecordInfoResp.class);
        recordRespList.forEach(this::fill);
        return recordRespList;
    }

    @Override
    protected void fill(Object obj) {
        super.fill(obj);
        if (!(obj instanceof StudyRoomAutoReserveTaskRecordInfoResp recordRespDTO)) {
            return;
        }
        StudyRoomAutoReserveTaskRecordDetailBO detail = JsonUtils
            .toBean(recordRespDTO.getDetailJson(), StudyRoomAutoReserveTaskRecordDetailBO.class);
        if (Objects.nonNull(detail)) {
            recordRespDTO.setSeatId(detail.getSeatId());
            recordRespDTO.setSeatNumber(detail.getSeatNumber());
            recordRespDTO.setTimeRange(detail.getTimeRange());
            recordRespDTO.setUserReserveId(detail.getUserReserveId());
            recordRespDTO.setFailMsg(detail.getFailMsg());
        }
    }
}

package top.continew.admin.business.study.service.autoreserve;

import top.continew.admin.business.study.model.query.StudyRoomAutoReserveTaskRecordQuery;
import top.continew.admin.business.study.model.req.StudyRoomAutoReserveTaskRecordReq;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskRecordInfoResp;
import top.continew.admin.common.base.service.BaseService;

import java.time.LocalDate;
import java.util.List;

/**
 * 自动预约执行记录服务
 *
 * @author bixiu
 * @since 4.2.0
 */
// @formatter:off
public interface StudyRoomAutoReserveTaskRecordService
    extends
    BaseService<StudyRoomAutoReserveTaskRecordInfoResp, StudyRoomAutoReserveTaskRecordInfoResp,
        StudyRoomAutoReserveTaskRecordQuery, StudyRoomAutoReserveTaskRecordReq> {
    // @formatter:on

    /**
     * 删除任务的全部执行历史，供任务硬删时级联使用
     *
     * @param taskId 任务 ID
     */
    void deleteByTaskId(Long taskId);

    /**
     * 写入一条执行记录
     *
     * @param taskRecordRespDTO 任务记录
     */
    void saveRecord(StudyRoomAutoReserveTaskRecordInfoResp taskRecordRespDTO);

    /**
     * 按主键更新一条执行记录
     *
     * @param taskRecordRespDTO 任务记录
     */
    void updateRecord(StudyRoomAutoReserveTaskRecordInfoResp taskRecordRespDTO);

    /**
     * 查询任务在指定日期的全部执行记录
     *
     * @param taskId 任务 ID
     * @param createDate 创建日期
     * @return 列表数据
     */
    List<StudyRoomAutoReserveTaskRecordInfoResp> listByTaskIdAndCreateDate(Long taskId,
        LocalDate createDate);
}

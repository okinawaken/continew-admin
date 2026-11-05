package top.continew.admin.business.study.service.autoreserve;

import top.continew.admin.business.study.model.query.StudyRoomAutoReserveTaskQuery;
import top.continew.admin.business.study.model.req.StudyRoomAutoReserveTaskReq;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.admin.common.base.service.BaseService;
import top.continew.starter.extension.tenant.annotation.TenantIgnore;

import java.time.LocalDate;
import java.util.List;

/**
 * 自动预约任务服务
 *
 * @author bixiu
 * @since 4.2.0
 */
public interface StudyRoomAutoReserveTaskService
    extends
    BaseService<StudyRoomAutoReserveTaskInfoResp, StudyRoomAutoReserveTaskInfoResp, StudyRoomAutoReserveTaskQuery, StudyRoomAutoReserveTaskReq> {

    /**
     * 以指定原因暂停任务
     *
     * @param taskId 任务 ID
     * @param pauseReason 暂停原因
     */
    void pauseTask(Long taskId, String pauseReason);

    /**
     * 恢复任务
     *
     * @param taskId 任务 ID
     */
    void resumeTask(Long taskId);

    /**
     * 手动执行一次
     *
     * @param taskId 任务 ID
     */
    void executeTask(Long taskId);

    /**
     * 查询全部启用中的任务
     *
     * @return 列表数据
     */
    @TenantIgnore
    List<StudyRoomAutoReserveTaskInfoResp> listEnabledTasks();

    /**
     * 条件推进放号基线日期
     *
     * @param taskId 任务 ID
     * @param targetDate 目标预约日期
     */
    void advanceLastMaxReserveDate(Long taskId, LocalDate targetDate);
}

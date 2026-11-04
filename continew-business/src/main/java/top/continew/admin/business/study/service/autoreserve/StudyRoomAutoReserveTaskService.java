package top.continew.admin.business.study.service.autoreserve;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.continew.admin.business.study.model.req.StudyRoomAutoReserveTaskReq;
import top.continew.admin.business.study.model.resp.StudyRoomAutoReserveTaskInfoResp;
import top.continew.starter.extension.crud.model.query.PageQuery;
import top.continew.starter.extension.tenant.annotation.TenantIgnore;

import java.time.LocalDate;
import java.util.List;

/**
 * 自动预约任务服务
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomAutoReserveTaskService {

    /**
     * 当前用户的任务分页
     *
     * @param userId 用户 ID
     * @param pageQuery 分页查询条件
     * @return 列表数据
     */
    IPage<StudyRoomAutoReserveTaskInfoResp> pageList(Long userId, PageQuery pageQuery);

    /**
     * 任务详情
     *
     * @param taskId 任务 ID
     * @return 详情数据
     */
    StudyRoomAutoReserveTaskInfoResp getTask(Long taskId);

    /**
     * 创建任务
     *
     * @param userId 用户 ID
     * @param dto 请求数据
     * @return 响应数据
     */
    Long createTask(Long userId, StudyRoomAutoReserveTaskReq dto);

    /**
     * 修改任务
     *
     * @param taskId 任务 ID
     * @param dto 请求数据
     */
    void updateTask(Long taskId, StudyRoomAutoReserveTaskReq dto);

    /**
     * 删除任务，同事务删除执行历史
     *
     * @param taskId 任务 ID
     */
    void deleteTask(Long taskId);

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

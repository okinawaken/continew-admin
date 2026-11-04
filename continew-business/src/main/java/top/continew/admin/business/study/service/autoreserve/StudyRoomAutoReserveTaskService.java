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
     * <p>
     * 内部执行链路使用，不做归属校验；面向用户的查询请使用 {@link #getTask(Long, Long)}。
     *
     * @param taskId 任务 ID
     * @return 详情数据
     */
    StudyRoomAutoReserveTaskInfoResp getTask(Long taskId);

    /**
     * 任务详情，校验任务归属当前用户
     *
     * @param taskId 任务 ID
     * @param userId 用户 ID
     * @return 详情数据
     */
    StudyRoomAutoReserveTaskInfoResp getTask(Long taskId, Long userId);

    /**
     * 创建任务
     *
     * @param userId 用户 ID
     * @param dto 请求数据
     * @return 响应数据
     */
    Long createTask(Long userId, StudyRoomAutoReserveTaskReq dto);

    /**
     * 修改任务，校验任务归属当前用户
     *
     * @param taskId 任务 ID
     * @param userId 用户 ID
     * @param dto 请求数据
     */
    void updateTask(Long taskId, Long userId, StudyRoomAutoReserveTaskReq dto);

    /**
     * 删除任务，同事务删除执行历史，校验任务归属当前用户
     *
     * @param taskId 任务 ID
     * @param userId 用户 ID
     */
    void deleteTask(Long taskId, Long userId);

    /**
     * 以指定原因暂停任务，校验任务归属当前用户
     *
     * @param taskId 任务 ID
     * @param userId 用户 ID
     * @param pauseReason 暂停原因
     */
    void pauseTask(Long taskId, Long userId, String pauseReason);

    /**
     * 恢复任务，校验任务归属当前用户
     *
     * @param taskId 任务 ID
     * @param userId 用户 ID
     */
    void resumeTask(Long taskId, Long userId);

    /**
     * 手动执行一次，校验任务归属当前用户
     *
     * @param taskId 任务 ID
     * @param userId 用户 ID
     */
    void executeTask(Long taskId, Long userId);

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

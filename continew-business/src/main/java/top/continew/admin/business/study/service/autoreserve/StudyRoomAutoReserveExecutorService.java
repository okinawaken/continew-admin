package top.continew.admin.business.study.service.autoreserve;

/**
 * 自动预约执行器
 *
 * @author bixiu
 * @since 4.2.0
 */
public interface StudyRoomAutoReserveExecutorService {

    /**
     * 执行一次自动预约，方法同步阻塞直到执行完成；异步由事件监听器负责
     *
     * @param taskId 任务 ID
     * @param triggerType 参数 triggerType
     */
    void execute(Long taskId, Integer triggerType);
}

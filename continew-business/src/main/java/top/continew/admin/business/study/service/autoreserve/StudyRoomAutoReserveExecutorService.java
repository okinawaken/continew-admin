package top.continew.admin.business.study.service.autoreserve;

/**
 * 自动预约执行器
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomAutoReserveExecutorService {

    /**
     * 提交一次执行，方法立即返回，实际执行在异步线程中进行
     *
     * @param taskId 任务 ID
     * @param triggerType 参数 triggerType
     */
    void execute(Long taskId, Integer triggerType);
}

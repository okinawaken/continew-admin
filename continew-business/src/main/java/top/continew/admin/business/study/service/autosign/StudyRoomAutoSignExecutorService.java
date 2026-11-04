package top.continew.admin.business.study.service.autosign;

/**
 * 自动签到执行器
 *
 * @author Amadeus
 * @since 4.2.0
 */
public interface StudyRoomAutoSignExecutorService {

    /**
     * 为指定用户执行一次自动签到
     *
     * @param userId 用户 ID
     */
    void execute(Long userId);
}

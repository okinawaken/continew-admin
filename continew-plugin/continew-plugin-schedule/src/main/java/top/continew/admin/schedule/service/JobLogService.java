package top.continew.admin.schedule.service;

import top.continew.admin.schedule.model.query.JobLogQuery;
import top.continew.admin.schedule.model.resp.JobLogResp;
import top.continew.starter.extension.crud.model.resp.PageResp;

/**
 * 任务日志业务接口
 *
 * @author KAI
 * @author Charles7c
 * @since 3.2.0
 */
public interface JobLogService {

    /**
     * 分页查询列表
     *
     * @param query 查询条件
     * @return 分页列表信息
     */
    PageResp<JobLogResp> page(JobLogQuery query);

    /**
     * 停止
     *
     * @param id ID
     * @return 停止结果
     */
    boolean stop(Long id);

    /**
     * 重试
     *
     * @param id ID
     * @return 重试结果
     */
    boolean retry(Long id);
}

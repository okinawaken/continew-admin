package top.continew.admin.schedule.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.schedule.api.JobBatchApi;
import top.continew.admin.schedule.api.JobClient;
import top.continew.admin.schedule.model.query.JobLogQuery;
import top.continew.admin.schedule.model.resp.JobLogResp;
import top.continew.admin.schedule.service.JobLogService;
import top.continew.starter.extension.crud.model.resp.PageResp;

/**
 * 任务日志业务实现
 *
 * @author bixiu
 * @author bixiu
 * @since 3.2.0
 */
@Service
@RequiredArgsConstructor
public class JobLogServiceImpl implements JobLogService {

    private final JobClient jobClient;
    private final JobBatchApi jobBatchApi;

    @Override
    public PageResp<JobLogResp> page(JobLogQuery query) {
        return jobClient.requestPage(() -> jobBatchApi.page(query));
    }

    @Override
    public boolean stop(Long id) {
        return Boolean.TRUE.equals(jobClient.request(() -> jobBatchApi.stop(id)));
    }

    @Override
    public boolean retry(Long id) {
        return Boolean.TRUE.equals(jobClient.request(() -> jobBatchApi.retry(id)));
    }
}

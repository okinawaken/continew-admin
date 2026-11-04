package top.continew.admin.schedule.model;

import com.aizuda.snailjob.common.core.model.Result;
import lombok.Data;

/**
 * 任务调度服务端分页响应参数
 *
 * @param <T> 数据类型
 * @author bixiu
 * @author bixiu
 * @since 3.2.0
 */
@Data
public class JobPageResult<T> extends Result<T> {

    /**
     * 页码
     */
    private long page;

    /**
     * 每页条数
     */
    private long size;

    /**
     * 总条数
     */
    private long total;
}

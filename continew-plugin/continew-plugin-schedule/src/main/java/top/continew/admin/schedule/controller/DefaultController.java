package top.continew.admin.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.continew.admin.schedule.annotation.ConditionalOnDisabledScheduleJob;
import top.continew.starter.web.model.R;

/**
 * 任务调度默认控制器
 *
 * @author bixiu
 * @since 4.1.0
 */
@RestController
@ConditionalOnDisabledScheduleJob
@RequestMapping({"/schedule/job", "/schedule/log"})
public class DefaultController {

    /**
     * 模块禁用时的兜底接口，显式声明接管标准 REST 方法并返回统一的"模块已禁用"提示
     *
     * @return 统一错误响应
     */
    @RequestMapping(value = "/**",
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
            RequestMethod.DELETE, RequestMethod.PATCH})
    public R error() {
        return R.fail(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR
            .value()), "任务模块已禁用，请于对应环境配置文件中配置 snail-job.enabled 为 true 进行启用");
    }
}

package top.continew.admin.config.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.continew.admin.system.mapper.LogMapper;
import top.continew.admin.system.service.UserService;
import top.continew.starter.log.annotation.ConditionalOnEnabledLog;
import top.continew.starter.log.dao.LogDao;
import top.continew.starter.trace.autoconfigure.TraceProperties;

/**
 * 日志配置
 *
 * @author Charles7c
 * @since 1.0.0
 */
@Configuration
@ConditionalOnEnabledLog
public class LogConfiguration {

    /**
     * 日志持久层接口本地实现类
     */
    @Bean
    public LogDao logDao(UserService userService, LogMapper logMapper,
        TraceProperties traceProperties) {
        return new DatabaseLogDao(userService, logMapper, traceProperties);
    }
}

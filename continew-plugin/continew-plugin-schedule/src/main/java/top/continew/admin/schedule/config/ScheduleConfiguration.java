package top.continew.admin.schedule.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 任务调度配置
 *
 * @author bixiu
 * @since 4.0.0
 */
@Configuration
public class ScheduleConfiguration {

    /**
     * API 文档分组配置
     */
    @Bean
    public GroupedOpenApi scheduleModuleApi() {
        return GroupedOpenApi.builder().group("schedule").displayName("任务调度")
            .pathsToMatch("/schedule/**").build();
    }
}

package top.continew.admin.schedule.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import top.continew.starter.core.constant.PropertiesConstants;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否启用 Snail Job 判断注解
 *
 * @author bixiu
 * @since 3.7.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(prefix = "snail-job", name = PropertiesConstants.ENABLED,
    havingValue = "true", matchIfMissing = true)
public @interface ConditionalOnEnabledScheduleJob {
}

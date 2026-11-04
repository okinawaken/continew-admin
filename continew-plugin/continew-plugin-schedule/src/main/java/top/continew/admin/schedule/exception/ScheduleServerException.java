package top.continew.admin.schedule.exception;

import top.continew.starter.core.exception.BaseException;

/**
 * 调度服务异常
 *
 * @author Charles7c
 * @since 3.7.0
 */
public class ScheduleServerException extends BaseException {

    public ScheduleServerException(String message) {
        super(message);
    }

    public ScheduleServerException(String message, Throwable cause) {
        super(message, cause);
    }
}

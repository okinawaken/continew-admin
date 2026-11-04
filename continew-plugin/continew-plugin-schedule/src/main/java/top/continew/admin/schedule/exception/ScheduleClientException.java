package top.continew.admin.schedule.exception;

import top.continew.starter.core.exception.BaseException;

/**
 * 调度客户端异常
 *
 * @author Charles7c
 * @since 3.7.0
 */
public class ScheduleClientException extends BaseException {

    public ScheduleClientException(String message) {
        super(message);
    }

    public ScheduleClientException(String message, Throwable cause) {
        super(message, cause);
    }
}

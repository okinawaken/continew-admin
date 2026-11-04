package top.continew.admin.common.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.continew.starter.auth.refresh.token.exception.RefreshTokenException;
import top.continew.starter.web.model.R;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Refresh Token HTTP 协议异常处理器
 *
 * @author bixiu
 * @since 4.2.0
 */
@Slf4j
@Order(98)
@RestControllerAdvice
public class RefreshTokenExceptionHandler {

    /**
     * 将认证会话协议异常转换为与 HTTP 状态一致的统一响应。
     *
     * @param e        Refresh Token 协议异常
     * @param request  当前请求
     * @param response 当前响应
     * @return 统一错误响应
     */
    @ExceptionHandler(RefreshTokenException.class)
    public R handle(RefreshTokenException e, HttpServletRequest request,
        HttpServletResponse response) {
        log.warn("[{}] {}：{}", request.getMethod(), request.getRequestURI(), e.getMessage());
        response.setStatus(e.getStatus().value());
        return R.fail(String.valueOf(e.getStatus().value()), e.getMessage());
    }
}

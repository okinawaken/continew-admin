package top.continew.admin.auth.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cn.dev33.satoken.stp.StpUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import top.continew.starter.auth.refresh.token.exception.RefreshTokenException;
import top.continew.starter.auth.refresh.token.service.RefreshAccessTokenIssuer;
import top.continew.starter.auth.refresh.token.service.RefreshTokenService;

/**
 * logout Cookie 安全边界测试
 *
 * @author Charles7c
 * @since 4.2.0
 */
class SessionControllerTest {

    private RefreshTokenService refreshTokenService;
    private SessionController controller;

    @BeforeEach
    void setUp() {
        refreshTokenService = mock(RefreshTokenService.class);
        controller =
            new SessionController(mock(RefreshAccessTokenIssuer.class), refreshTokenService);
    }

    @Test
    void shouldClearMalformedCookieForTrustedRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        RefreshTokenException malformed = RefreshTokenException.forbidden("Cookie 损坏");
        when(refreshTokenService.resolve(null, request)).thenThrow(malformed);

        try (MockedStatic<StpUtil> stpUtil = mockStatic(StpUtil.class)) {
            stpUtil.when(() -> StpUtil.getLoginId(-1L)).thenReturn(1L);
            stpUtil.when(StpUtil::getTokenValue).thenReturn(null);

            assertThrows(RefreshTokenException.class,
                () -> controller.logout(null, request, response));
        }

        verify(refreshTokenService).validateCookieOrigin(request);
        verify(refreshTokenService).clearCookie(response);
    }

    @Test
    void shouldNotClearCookieForUntrustedRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        doThrow(RefreshTokenException.forbidden("请求来源不合法"))
            .when(refreshTokenService)
            .validateCookieOrigin(request);

        try (MockedStatic<StpUtil> stpUtil = mockStatic(StpUtil.class)) {
            stpUtil.when(() -> StpUtil.getLoginId(-1L)).thenReturn(1L);
            stpUtil.when(StpUtil::getTokenValue).thenReturn(null);

            assertThrows(RefreshTokenException.class,
                () -> controller.logout(null, request, response));
        }

        verify(refreshTokenService, never()).resolve(any(), any());
        verify(refreshTokenService, never()).clearCookie(response);
    }

    @Test
    void shouldKeepCookieWhenSessionRevocationFails() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        when(refreshTokenService.resolve(null, request)).thenReturn("session.secret");
        doThrow(new IllegalStateException("Redis unavailable"))
            .when(refreshTokenService)
            .revokeCurrent("access-token", "session.secret");

        try (MockedStatic<StpUtil> stpUtil = mockStatic(StpUtil.class)) {
            stpUtil.when(() -> StpUtil.getLoginId(-1L)).thenReturn(1L);
            stpUtil.when(StpUtil::getTokenValue).thenReturn("access-token");

            assertThrows(IllegalStateException.class,
                () -> controller.logout(null, request, response));
        }

        verify(refreshTokenService, never()).clearCookie(response);
    }
}

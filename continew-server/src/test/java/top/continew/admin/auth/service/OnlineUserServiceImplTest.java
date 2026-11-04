package top.continew.admin.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import top.continew.admin.auth.model.query.OnlineUserQuery;
import top.continew.admin.auth.model.resp.OnlineUserResp;
import top.continew.admin.auth.service.impl.OnlineUserServiceImpl;
import top.continew.starter.auth.refresh.token.model.RefreshSession;
import top.continew.starter.auth.refresh.token.model.SessionView;
import top.continew.starter.auth.refresh.token.service.SessionInvalidationService;
import top.continew.starter.auth.refresh.token.service.SessionQueryService;
import top.continew.starter.extension.tenant.context.TenantContextHolder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * 在线用户以 Refresh Session 为事实源测试
 *
 * @author bixiu
 * @since 4.2.0
 */
class OnlineUserServiceImplTest {

    @Test
    void shouldListRefreshSessionAfterAccessTokenExpires() {
        SessionInvalidationService sessionInvalidationService =
            mock(SessionInvalidationService.class);
        SessionQueryService sessionQueryService = mock(SessionQueryService.class);
        RefreshSession refreshSession = new RefreshSession();
        refreshSession.setSessionId("session-1");
        refreshSession.setUserId(1L);
        refreshSession.setUsername("tester");
        refreshSession.setNickname("Tester");
        refreshSession.setClientId("web");
        refreshSession.setClientType("WEB");
        refreshSession.setCreatedAt(1_767_262_400_000L);
        refreshSession.setLastRefreshAt(1_767_266_000_000L);
        SessionView session = SessionView.from(refreshSession);
        when(sessionQueryService.listSessions(null)).thenReturn(List.of(session));

        OnlineUserServiceImpl service = new OnlineUserServiceImpl(sessionInvalidationService,
            sessionQueryService);
        try (MockedStatic<TenantContextHolder> tenantHolder = mockStatic(
            TenantContextHolder.class)) {
            tenantHolder.when(TenantContextHolder::isTenantEnabled).thenReturn(false);

            List<OnlineUserResp> result = service.list(new OnlineUserQuery());

            assertEquals(1, result.size());
            assertEquals("session-1", result.get(0).getSessionId());
            assertEquals(toLocalDateTime(session.getCreatedAt()), result.get(0).getLoginTime());
            assertEquals(toLocalDateTime(session.getLastRefreshAt()),
                result.get(0).getLastRefreshTime());
        }
    }

    private LocalDateTime toLocalDateTime(long epochMilli) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
    }
}

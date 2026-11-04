package top.continew.admin.auth.constant;

/**
 * 认证相关常量
 *
 * @author bixiu
 * @since 4.0.0
 */
public class AuthConstants {

    /**
     * 登录 URI
     */
    public static final String LOGIN_URI = "/auth/login";

    /**
     * 登出 URI
     */
    public static final String LOGOUT_URI = "/auth/logout";

    /**
     * 刷新令牌 URI
     */
    public static final String REFRESH_URI = "/auth/refresh";

    private AuthConstants() {
    }
}

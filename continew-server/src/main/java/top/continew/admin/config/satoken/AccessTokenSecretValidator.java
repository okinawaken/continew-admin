package top.continew.admin.config.satoken;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 启动时校验认证密钥，禁止生产认证使用弱密钥或复用密钥
 *
 * @author luoqiz
 * @since 4.2.0
 */
@Component
@RequiredArgsConstructor
public class AccessTokenSecretValidator {

    private static final int MIN_SECRET_LENGTH = 32;

    private final Environment environment;

    @PostConstruct
    public void validate() {
        String accessSecret = this.bindSecret("sa-token.jwt-secret-key",
            "Access Token JWT 密钥");
        String refreshSecret = this.bindSecret("continew-starter.refresh-token.secret",
            "Refresh Token 密钥");
        if (MessageDigest.isEqual(accessSecret.getBytes(StandardCharsets.UTF_8),
            refreshSecret.getBytes(StandardCharsets.UTF_8))) {
            throw new IllegalStateException(
                "Access Token 与 Refresh Token 密钥必须相互独立，禁止复用同一密钥");
        }
    }

    private String bindSecret(String propertyName, String displayName) {
        String secret = Binder.get(environment).bind(propertyName, String.class).orElse("");
        if (secret.length() < MIN_SECRET_LENGTH) {
            throw new IllegalStateException(displayName + "长度不能少于 32 个字符");
        }
        return secret;
    }
}

package top.continew.admin.auth.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Refresh Token 请求参数。
 *
 * <p>Web 客户端不填写该字段，后端从 HttpOnly Cookie 读取；App、微信小程序通过该字段
 * 提交安全存储的 Refresh Token。</p>
 *
 * @author luoqiz
 */
@Data
@Schema(description = "Refresh Token 请求参数")
public class RefreshTokenReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * App / 小程序提交的 Refresh Token，浏览器模式为空
     */
    @Schema(description = "Refresh Token（浏览器 Cookie 模式不填写）")
    private String refreshToken;
}

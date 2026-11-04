package top.continew.admin.auth.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serial;

/**
 * 账号登录请求参数
 *
 * @author bixiu
 * @since 1.0.0
 */
@Data
@Schema(description = "账号登录请求参数")
public class AccountLoginReq extends LoginReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @Schema(description = "用户名", example = "zhangsan")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码", example = "RSA 公钥加密的密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @Schema(description = "验证码", example = "ABCD")
    private String captcha;

    /**
     * 验证码标识
     */
    @Schema(description = "验证码标识", example = "090b9a2c-1691-4fca-99db-e4ed0cff362f")
    private String uuid;
}

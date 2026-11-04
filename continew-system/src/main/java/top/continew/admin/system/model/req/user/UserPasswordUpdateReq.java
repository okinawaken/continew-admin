package top.continew.admin.system.model.req.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

/**
 * 用户密码修改请求参数
 *
 * @author Charles7c
 * @since 1.0.0
 */
@Data
@Schema(description = "用户密码修改请求参数")
public class UserPasswordUpdateReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 当前密码
     */
    @Schema(description = "当前密码", example = "RSA 公钥加密的当前密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @Schema(description = "新密码", example = "RSA 公钥加密的新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}

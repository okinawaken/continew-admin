package top.continew.admin.system.model.req.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

/**
 * 用户邮箱修改请求参数
 *
 * @author bixiu
 * @since 1.0.0
 */
@Data
@Schema(description = "用户邮箱修改请求参数")
public class UserEmailUpdateReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 新邮箱
     */
    @Schema(description = "新邮箱", example = "123456789@qq.com")
    @NotBlank(message = "新邮箱不能为空")
    @Email(message = "新邮箱格式不正确")
    private String email;

    /**
     * 验证码
     */
    @Schema(description = "验证码", example = "888888")
    @NotBlank(message = "验证码不能为空")
    @Length(max = 6, message = "验证码无效")
    private String captcha;

    /**
     * 当前密码
     */
    @Schema(description = "当前密码", example = "RSA 公钥加密的当前密码")
    @NotBlank(message = "当前密码不能为空")
    private String oldPassword;
}

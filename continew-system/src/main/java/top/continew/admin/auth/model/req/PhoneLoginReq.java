package top.continew.admin.auth.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.validation.constraints.Mobile;

import jakarta.validation.constraints.NotBlank;
import java.io.Serial;

/**
 * 手机号登录请求参数
 *
 * @author bixiu
 * @since 1.3.0
 */
@Data
@Schema(description = "手机号登录请求参数")
public class PhoneLoginReq extends LoginReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    @Schema(description = "手机号", example = "13811111111")
    @NotBlank(message = "手机号不能为空")
    @Mobile
    private String phone;

    /**
     * 验证码
     */
    @Schema(description = "验证码", example = "888888")
    @NotBlank(message = "验证码不能为空")
    @Length(max = 6, message = "验证码无效")
    private String captcha;
}

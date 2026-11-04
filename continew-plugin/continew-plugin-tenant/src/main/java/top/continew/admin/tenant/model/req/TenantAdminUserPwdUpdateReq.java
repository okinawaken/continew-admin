package top.continew.admin.tenant.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 租户管理员密码修改请求参数
 *
 * @author bixiu
 * @since 3.4.1
 */
@Data
@Schema(description = "租户管理员密码修改请求参数")
public class TenantAdminUserPwdUpdateReq implements Serializable {

    /**
     * 新密码
     */
    @Schema(description = "新密码", example = "RSA 公钥加密的新密码")
    @NotBlank(message = "新密码不能为空")
    private String password;
}

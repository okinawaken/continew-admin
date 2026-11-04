package top.continew.admin.system.model.req.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 用户角色修改请求参数
 *
 * @author bixiu
 * @since 1.0.0
 */
@Data
@Schema(description = "用户角色修改请求参数")
public class UserRoleUpdateReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色 ID 列表
     */
    @Schema(description = "所属角色", example = "1,2")
    @NotEmpty(message = "所属角色不能为空")
    private List<Long> roleIds;
}

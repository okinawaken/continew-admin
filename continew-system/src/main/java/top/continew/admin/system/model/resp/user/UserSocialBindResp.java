package top.continew.admin.system.model.resp.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 第三方账号绑定响应参数
 *
 * @author bixiu
 * @since 1.3.0
 */
@Data
@Schema(description = "第三方账号绑定响应参数")
public class UserSocialBindResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 来源
     */
    @Schema(description = "来源", example = "GITEE")
    private String source;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "码云")
    private String description;
}

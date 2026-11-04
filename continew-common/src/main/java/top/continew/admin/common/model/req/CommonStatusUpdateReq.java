package top.continew.admin.common.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.common.enums.DisEnableStatusEnum;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 状态修改请求参数
 *
 * @author bixiu
 * @since 4.0.0
 */
@Data
@Schema(description = "状态修改请求参数")
public class CommonStatusUpdateReq implements Serializable {

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    @NotNull(message = "状态无效")
    private DisEnableStatusEnum status;
}

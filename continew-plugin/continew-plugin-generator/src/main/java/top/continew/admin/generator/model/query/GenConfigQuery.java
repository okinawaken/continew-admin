package top.continew.admin.generator.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 生成配置查询条件
 *
 * @author bixiu
 * @since 1.1.0
 */
@Data
@Schema(description = "生成配置查询条件")
public class GenConfigQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    @Schema(description = "表名称", example = "sys_user")
    private String tableName;
}

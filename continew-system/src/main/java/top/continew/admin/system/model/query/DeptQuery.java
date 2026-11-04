package top.continew.admin.system.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.starter.data.annotation.Query;
import top.continew.starter.data.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;

/**
 * 部门查询条件
 *
 * @author bixiu
 * @since 1.0.0
 */
@Data
@Schema(description = "部门查询条件")
public class DeptQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关键词
     */
    @Schema(description = "关键词", example = "测试部")
    @Query(columns = {"name", "description"}, type = QueryType.LIKE)
    private String description;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    @Query(type = QueryType.EQ)
    private DisEnableStatusEnum status;
}

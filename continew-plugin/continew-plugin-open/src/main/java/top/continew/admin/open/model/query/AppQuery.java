package top.continew.admin.open.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.data.annotation.Query;
import top.continew.starter.data.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;

/**
 * 应用查询条件
 *
 * @author bixiu
 * @author bixiu
 * @since 3.4.0
 */
@Data
@Schema(description = "应用查询条件")
public class AppQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关键词
     */
    @Schema(description = "关键词", example = "应用1")
    @Query(columns = {"name", "description"}, type = QueryType.LIKE)
    private String description;
}

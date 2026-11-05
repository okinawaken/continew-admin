package top.continew.admin.business.study.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.data.annotation.Query;
import top.continew.starter.data.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;

/**
 * 自动预约任务查询条件
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@Schema(description = "自动预约任务查询条件")
public class StudyRoomAutoReserveTaskQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 门店 ID
     */
    @Schema(description = "门店 ID", example = "1")
    @Query(type = QueryType.EQ)
    private Integer venueId;

    /**
     * 启用状态：0 暂停，1 启用
     */
    @Schema(description = "启用状态：0 暂停，1 启用", example = "1")
    @Query(type = QueryType.EQ)
    private Integer enableStatus;
}

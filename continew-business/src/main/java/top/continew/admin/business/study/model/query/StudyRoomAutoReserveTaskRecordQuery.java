package top.continew.admin.business.study.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.data.annotation.Query;
import top.continew.starter.data.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 自动预约执行记录查询条件
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@Schema(description = "自动预约执行记录查询条件")
public class StudyRoomAutoReserveTaskRecordQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务 ID
     */
    @Schema(description = "任务 ID", example = "1")
    @Query(type = QueryType.EQ)
    private Long taskId;

    /**
     * 系统用户 ID
     */
    @Schema(description = "系统用户 ID", example = "1")
    @Query(type = QueryType.EQ)
    private Long userId;

    /**
     * 触发方式：0 定时，1 手动
     */
    @Schema(description = "触发方式：0 定时，1 手动", example = "1")
    @Query(type = QueryType.EQ)
    private Integer triggerType;

    /**
     * 目标预约日期
     */
    @Schema(description = "目标预约日期", example = "2026-11-05", type = "string")
    @Query(type = QueryType.EQ)
    private LocalDate targetDate;

    /**
     * 执行状态：0 跳过，1 成功，2 失败
     */
    @Schema(description = "执行状态：0 跳过，1 成功，2 失败", example = "1")
    @Query(type = QueryType.EQ)
    private Integer status;
}

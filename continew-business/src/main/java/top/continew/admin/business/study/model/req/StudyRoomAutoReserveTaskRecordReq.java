package top.continew.admin.business.study.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

/**
 * 自动预约执行记录创建或修改请求
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@Schema(description = "自动预约执行记录创建或修改请求参数")
public class StudyRoomAutoReserveTaskRecordReq {

    /**
     * 任务 ID
     */
    @Schema(description = "任务 ID", example = "1")
    private Long taskId;

    /**
     * 系统用户 ID
     */
    @Schema(description = "系统用户 ID", example = "1")
    private Long userId;

    /**
     * 触发方式：0 定时，1 手动
     */
    @Schema(description = "触发方式：0 定时，1 手动", example = "1")
    private Integer triggerType;

    /**
     * 目标预约日期
     */
    @Schema(description = "目标预约日期", example = "2026-11-05", type = "string")
    private LocalDate targetDate;

    /**
     * 执行状态：0 跳过，1 成功，2 失败
     */
    @Schema(description = "执行状态：0 跳过，1 成功，2 失败", example = "1")
    private Integer status;

    /**
     * 实际预约座位ID
     */
    @Schema(description = "实际预约座位 ID", example = "1")
    private Long seatId;

    /**
     * 实际预约座位编号
     */
    @Schema(description = "实际预约座位编号", example = "A01")
    private String seatNumber;

    /**
     * 实际预约时段 {@code HH:mm-HH:mm}
     */
    @Schema(description = "实际预约时段", example = "08:00-22:00")
    private String timeRange;

    /**
     * 上游预约单ID
     */
    @Schema(description = "上游预约单 ID", example = "1")
    private Long userReserveId;

    /**
     * 失败原因描述
     */
    @Schema(description = "失败原因描述", example = "座位已被预约")
    private String failMsg;
}

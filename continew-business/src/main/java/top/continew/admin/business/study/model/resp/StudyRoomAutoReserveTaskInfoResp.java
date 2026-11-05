package top.continew.admin.business.study.model.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 自动预约任务结果模型
 * <p>
 * 由任务 PO 与 {@code task_json} 规则合并而来，页面接口与调度、执行器共用。
 *
 * @author bixiu
 * @since 4.2.0
 */
@Data
@Schema(description = "自动预约任务响应参数")
public class StudyRoomAutoReserveTaskInfoResp {

    /**
     * 主键
     */
    @Schema(description = "主键", example = "1")
    private Long id;

    /**
     * 系统用户ID
     */
    @Schema(description = "系统用户 ID", example = "1")
    private Long userId;

    /**
     * 租户 ID（定时任务跨租户扫描时需要）
     */
    @Schema(description = "租户 ID", example = "0")
    private Long tenantId;

    /**
     * 门店 ID
     */
    @Schema(description = "门店 ID", example = "1")
    private Integer venueId;

    /**
     * 门店名称
     */
    @Schema(description = "门店名称", example = "示例门店")
    private String venueName;

    /**
     * 指定座位ID，0 表示未指定
     */
    @Schema(description = "指定座位 ID，0 表示未指定", example = "1")
    private Long seatId;

    /**
     * 指定座位编号
     */
    @Schema(description = "指定座位编号", example = "A01")
    private String seatNumber;

    /**
     * 偏好区域ID，0 表示未指定
     */
    @Schema(description = "偏好区域 ID，0 表示未指定", example = "1")
    private Long areaId;

    /**
     * 偏好区域名称
     */
    @Schema(description = "偏好区域名称", example = "安静区")
    private String areaName;

    /**
     * 期望开始时间 {@code HH:mm}
     */
    @Schema(description = "期望开始时间", example = "08:00", type = "string")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveStartTime;

    /**
     * 期望结束时间 {@code HH:mm}
     */
    @Schema(description = "期望结束时间", example = "22:00", type = "string")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveEndTime;

    /**
     * 执行星期，1 周一 … 7 周日
     */
    @Schema(description = "执行星期，1 周一 … 7 周日", example = "[1,2,3,4,5]")
    private List<Integer> weekDays;

    /**
     * 启用状态：0 暂停，1 启用
     */
    @Schema(description = "启用状态：0 暂停，1 启用", example = "1")
    private Integer enableStatus;

    /**
     * 暂停原因
     */
    @Schema(description = "暂停原因", example = "用户手动暂停")
    private String pauseReason;

    /**
     * 放号基线日期
     */
    @Schema(description = "放号基线日期", example = "2026-11-05", type = "string")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastMaxReserveDate;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2026-11-05 08:00:00", type = "string")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 任务规则 JSON，仅用于数据填充，不对外输出
     */
    @JsonIgnore
    @Schema(hidden = true)
    private String taskJson;
}
